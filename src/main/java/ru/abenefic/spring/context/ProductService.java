package ru.abenefic.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void create(String title, float cost) {
        productRepository.create(title, cost);
    }

    public Product get(int id) {
        return productRepository.get(id);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public void update(int id, String title, float cost) {
        productRepository.update(id, title, cost);
    }

    public void delete(int id) {
        productRepository.delete(id);
    }

    public int count() {
        return getAll().size();
    }

    public float averageCost() {
        List<Product> products = getAll();
        float avg = 0;
        for (Product product : products) {
            avg += product.getCost();
        }
        return avg;
    }
}
