package ru.abenefic.spring.shop.product;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = "ru.abenefic.spring")
public class MsProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductApplication.class, args);
    }
}
