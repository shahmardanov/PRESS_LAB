package com.example.press_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PressLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(PressLabApplication.class, args);
    }

}
