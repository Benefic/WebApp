package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private long id;

    private ProductDto product;
    private String productTitle;
    private float count;
    private float cost;
    private float sum;
}
