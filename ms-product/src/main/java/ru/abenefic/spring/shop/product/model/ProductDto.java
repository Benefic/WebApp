package ru.abenefic.spring.shop.product.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private float cost;
    private String categoryName;

    // это чтобы добавлять товары можно было с категорией, зная её id.
    // Хотя, возможно, лучше её назначить отдельным методом...
    private Long categoryId;
}
