package com.diet.api.service;

import com.diet.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticate(String email, String senha, String dataNascimento) {
        
        User user = userService.findByEmail(email).orElse(null);
        
        if (user == null) {
            return false;
        }

        String salt = user.getDataNascimento().toString();

        return passwordEncoder.matches(senha + salt, user.getSenha());
    }
}
