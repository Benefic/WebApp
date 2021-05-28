package ru.abenefic.spring.shop.core.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long id;
    private long user_id;
    private float summ;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Collection<OrderItemDto> orderItems;
}
