package com.api.controller;

import com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Map<String, Object> getUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("nome", "João");
        user.put("email", "joao@email.com");
        return user;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String dataNascimento) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dataNascimento, formatter);

        try {
            userService.registerUser(nome, email, senha, dateTime);
            return ResponseEntity.ok("Cadastro realizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
        try {
            boolean isAuthenticated = userService.loginUser(email, senha);
            if (isAuthenticated) {
                return ResponseEntity.ok("Login realizado com sucesso!");
            } else {
                return ResponseEntity.status(401).body("Senha incorreta!");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}

