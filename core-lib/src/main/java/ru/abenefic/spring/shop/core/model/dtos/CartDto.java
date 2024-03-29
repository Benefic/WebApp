package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private UUID id;
    private long user_id;

    private float summ;
    private List<CartItemDto> cartItems;

}
