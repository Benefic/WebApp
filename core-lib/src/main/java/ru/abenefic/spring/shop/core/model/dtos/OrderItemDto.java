package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderItemDto {

    private long id;
    private OrderDto order;

    private ProductItemDto productItem;
    private float count;
    private float cost;
    private float sum;
}
