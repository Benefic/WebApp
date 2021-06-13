package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private UUID cart;
    private long id;
    private long productId;
    private String productTitle;
    private float count;
    private float cost;
    private float sum;

}
