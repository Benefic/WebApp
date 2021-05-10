package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDto {
    private String login;
    private String password;
}
