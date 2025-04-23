package com.diet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.diet.api.model.User;
import com.diet.api.model.User.Role;
import com.diet.api.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public User registerUser(String nome, String email, String senha, LocalDateTime dataNascimento, Role role) {
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        user.setDataNascimento(dataNascimento);  
        user.setRole(role);
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String email, String senha) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(senha, user.get().getSenha())) {
            return user;
        }
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email); 
    }
}
