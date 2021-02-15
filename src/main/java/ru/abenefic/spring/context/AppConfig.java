package ru.abenefic.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.abenefic.spring.context")
public class AppConfig {
    @Autowired
    private ProductService productService;

}
