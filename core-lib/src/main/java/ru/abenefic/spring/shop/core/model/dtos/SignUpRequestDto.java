package ru.abenefic.spring.shop.core.model.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String login;
    private String password;
}
