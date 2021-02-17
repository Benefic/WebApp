package ru.abenefic.spring.context;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int id;
    private String title;
    private float cost;

    // Можно было @ToString заюзать, но вывод тогда некрасивый по цене
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + String.format("%.2f", cost) +
                '}';
    }
}
