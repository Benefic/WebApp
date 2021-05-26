package ru.abenefic.spring.shop.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.abenefic.spring")
public class MsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAuthApplication.class, args);
    }

}

