package ru.abenefic.spring.repositories;

import ru.abenefic.spring.entities.Product;

import java.util.List;

public interface ProductRepository {
    void create(String title, float cost);

    Product get(int id);

    List<Product> getAll();

    void update(int id, String title, float cost);

    void delete(int id);

    void save(Product product);
}