package com.diet.api.controller;

import com.diet.api.model.User;
import com.diet.api.service.UserService;
import com.diet.api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User register(@RequestBody UserRegistrationRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 

        //
        LocalDateTime dataNascimento = LocalDateTime.parse(request.getDataNascimento(), formatter);

        return userService.registerUser(
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                dataNascimento,
                User.Role.MEMBER
        );
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserLoginRequest request) {
        return authenticationService.authenticate(
                request.getEmail(),
                request.getSenha(),
                request.getDataNascimento()
        );
    }

    public static class UserRegistrationRequest {
        private String nome;
        private String email;
        private String senha;
        private String dataNascimento;

     
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }
    }

    public static class UserLoginRequest {
        private String email;
        private String senha;
        private String dataNascimento;

       
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }
    }
}
