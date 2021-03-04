package ru.abenefic.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abenefic.spring.model.Product;
import ru.abenefic.spring.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllByCostBetween(float first, float second) {
        return productRepository.findProductsByCostBetween(first, second);
    }

    public List<Product> getAllByCostIsLessThanEqual(float first) {
        return productRepository.findProductsByCostIsLessThanEqual(first);
    }

    public List<Product> getAllByCostGreaterThanEqual(float first) {
        return productRepository.findProductsByCostGreaterThanEqual(first);
    }

    public List<Product> getAllByTitleContains(String title) {
        return productRepository.findProductsByTitleContainsIgnoreCase(title);
    }

    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product add(Product student) {
        return productRepository.save(student);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
