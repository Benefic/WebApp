package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private long id;

    private CartDto cart;

    private ProductDto product;
    private float count;
    private float cost;
    private float sum;

}
