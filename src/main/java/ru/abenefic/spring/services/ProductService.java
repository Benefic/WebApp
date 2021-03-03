package ru.abenefic.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.entities.Product;
import ru.abenefic.spring.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(String title, float cost) {
        productRepository.create(title, cost);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> get(int id) {
        return Optional.ofNullable(productRepository.get(id));
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
