package ru.abenefic.spring.shop.core.model.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductItemDto {
    private long id;

    @NonNull
    private ProductDto product;
}
