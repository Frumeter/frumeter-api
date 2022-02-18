package com.bdweb.frumeterapi.controller;

import com.bdweb.frumeterapi.model.User;
import com.bdweb.frumeterapi.model.dto.AnswerLogin;
import com.bdweb.frumeterapi.model.dto.CredentialsLoginDTO;
import com.bdweb.frumeterapi.model.dto.CredentialsRegisterDTO;
import com.bdweb.frumeterapi.model.dto.CredentialsResetPasswordDTO;
import com.bdweb.frumeterapi.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid CredentialsLoginDTO credentialsLoginDTO) {
        try {
            AnswerLogin answerLogin = this.authenticationService.login(credentialsLoginDTO.getEmail(), credentialsLoginDTO.getPassword());
            return new ResponseEntity<>(answerLogin, HttpStatus.OK);
        }  catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível fazer login, credenciais inválidas.");
        } catch (Exception e) {
            log.error("Erro ao fazer login", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao fazer login");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CredentialsRegisterDTO credentialsRegisterDTO) {
        try {
            User newUser = this.authenticationService.register(credentialsRegisterDTO);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erro ao fazer cadastro" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário com esse email.");
        }
    }

    @PatchMapping("/password/change")
    public boolean changePassword(@RequestBody @Valid CredentialsResetPasswordDTO credentialsResetPasswordDTO) {
        try {
            return authenticationService
                    .changePassword(credentialsResetPasswordDTO.getPassword(), credentialsResetPasswordDTO.getConfirmationPassword(), credentialsResetPasswordDTO.getEmail());
        } catch (Exception e) {
            log.error("Erro ao alterar senha" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao alterar a senha, tente novamente.");
        }
    }

}
