package ru.abenefic.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        SpringApplication.run(ru.abenefic.spring.WebApplication.class, args);
    }

}
