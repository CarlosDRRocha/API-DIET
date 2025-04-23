package com.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePassword(String password, String salt) {
        return passwordEncoder.encode(password + salt);
    }

    public boolean matches(String rawPassword, String encodedPassword, String salt) {
        return passwordEncoder.matches(rawPassword + salt, encodedPassword);
    }
}
