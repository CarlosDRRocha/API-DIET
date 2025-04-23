package com.api.service;

import com.api.model.User;
import com.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    public User registerUser(String nome, String email, String senha, LocalDateTime dataNascimento) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email já cadastrado!");
        }

               String salt = dataNascimento.toString();
        String senhaCodificada = passwordService.encodePassword(senha, salt);

        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setDataNascimento(dataNascimento);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(User.Role.MEMBER);
        user.setHash_password(senhaCodificada);

        return userRepository.save(user);
    }

    public boolean loginUser(String email, String senha) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        String salt = user.getDataNascimento().toString();
        return passwordService.matches(senha, user.getHash_password(), salt);
    }
}
