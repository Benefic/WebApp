package ru.abenefic.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.abenefic.spring.model.Product;
import ru.abenefic.spring.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(int page, int size, Sort sort) {
        return productRepository.findAll(PageRequest.of(page, size, sort));
    }

    public Page<Product> getAllByCostBetween(float first, float second, int page, int size, Sort sort) {
        return productRepository.findProductsByCostBetween(first, second, PageRequest.of(page, size, sort));
    }

    public Page<Product> getAllByCostIsLessThanEqual(float first, int page, int size, Sort sort) {
        return productRepository.findProductsByCostIsLessThanEqual(first, PageRequest.of(page, size, sort));
    }

    public Page<Product> getAllByCostGreaterThanEqual(float first, int page, int size, Sort sort) {
        return productRepository.findProductsByCostGreaterThanEqual(first, PageRequest.of(page, size, sort));
    }

    public Page<Product> getAllByTitleContains(String title, int page, int size, Sort sort) {
        return productRepository.findProductsByTitleContainsIgnoreCase(title, PageRequest.of(page, size, sort));
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
