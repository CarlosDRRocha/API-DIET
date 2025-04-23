package com.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String hash_password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime dataNascimento;


    public enum Role {
        ADM, MEMBER
    }
}
