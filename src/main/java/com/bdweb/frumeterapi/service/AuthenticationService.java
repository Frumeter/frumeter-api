package com.bdweb.frumeterapi.service;

import com.bdweb.frumeterapi.config.security.UserDetailsData;
import com.bdweb.frumeterapi.config.security.jwt.JWTUtil;
import com.bdweb.frumeterapi.model.User;
import com.bdweb.frumeterapi.model.dto.AnswerLogin;
import com.bdweb.frumeterapi.model.dto.CredentialsRegisterDTO;
import com.bdweb.frumeterapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public AnswerLogin login(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        UserDetailsData userDetailsData = (UserDetailsData) auth.getPrincipal();
        String jwtToken = jwtUtil.generateToken(((UserDetailsData) auth.getPrincipal()).getUsername());

        return new AnswerLogin(userDetailsData, jwtToken);
    }

    public User register(CredentialsRegisterDTO credentialsRegisterDTO) {
        Optional<User> user = userRepository.findByEmail(credentialsRegisterDTO.getEmail());

        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Já existe um usuário com esse email.");
        }

        String encodedPassword = passwordEncoder.encode(credentialsRegisterDTO.getPassword());

        User newUser = User.builder()
                .email(credentialsRegisterDTO.getEmail())
                .password(encodedPassword)
                .name(credentialsRegisterDTO.getName())
                .birthDate(credentialsRegisterDTO.getBithDate())
                .build();

        return userRepository.save(newUser);
    }

    public boolean changePassword(@NotNull String password, @NotNull String confirmationPassword, @NotNull String email) {
        if (password.equals(confirmationPassword)) {
            Optional<User> user = userRepository.findByEmail(email);

            if (user.isPresent()) {
                String encodedPassword = passwordEncoder.encode(password);

                user.get().setPassword(encodedPassword);
                userRepository.save(user.get());
                return true;
            } else {
                log.info("O email informado não pertence a nenhum usuário");
                return false;
            }
        } else {
            log.info("Senhas divergentes, não foi possível alterar senha.");
            return false;
        }

    }
}

