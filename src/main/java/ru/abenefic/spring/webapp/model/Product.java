package ru.abenefic.spring.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {
    private int id;
    private String title;
    private float cost;
}