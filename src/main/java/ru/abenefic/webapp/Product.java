package ru.abenefic.webapp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    int id;
    String title;
    float cost;

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
