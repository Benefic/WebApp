package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long id;
    private long user_id;
    private float summ;
    private String address;
    private LocalDateTime createdAt;

    private List<OrderItemDto> orderItems;
}
