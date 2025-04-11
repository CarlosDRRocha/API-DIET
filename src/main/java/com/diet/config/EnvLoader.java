package com.diet.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EnvLoader {

    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

        String dbUrl = dotenv.get("DB_URL");
        String dbUser = dotenv.get("DB_USER");
        String dbPass = dotenv.get("DB_PASS");

        System.out.println(">>> DB_URL from .env: " + dbUrl);
        System.out.println(">>> DB_USER from .env: " + dbUser);
        System.out.println(">>> DB_PASS from .env: " + dbPass);

        if (dbUrl != null) System.setProperty("DB_URL", dbUrl);
        if (dbUser != null) System.setProperty("DB_USER", dbUser);
        if (dbPass != null) System.setProperty("DB_PASS", dbPass);
    }
}
