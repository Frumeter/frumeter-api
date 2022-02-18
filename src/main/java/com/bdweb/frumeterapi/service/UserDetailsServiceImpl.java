package com.bdweb.frumeterapi.service;

import com.bdweb.frumeterapi.config.security.UserDetailsData;
import com.bdweb.frumeterapi.model.User;
import com.bdweb.frumeterapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {

        Optional<User> userByEmail = userRepository.findByEmail(email);

        return userByEmail
                .map(UserDetailsData::fromUser)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
