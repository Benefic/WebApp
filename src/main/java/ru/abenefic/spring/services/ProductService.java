package ru.abenefic.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.abenefic.spring.exceptions.ResourceNotFoundException;
import ru.abenefic.spring.model.ProductMapper;
import ru.abenefic.spring.model.dtos.ProductDto;
import ru.abenefic.spring.model.entities.Product;
import ru.abenefic.spring.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public Page<ProductDto> getAll(Specification<Product> spec, int page, int size, Sort sort) {
        return productRepository.findAll(spec, PageRequest.of(page, size, sort)).map(mapper::toDto);
    }

    public ProductDto getById(Long id) {
        Optional<ProductDto> product = productRepository.findById(id).map(mapper::toDto);
        return product.orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", id)));
    }

    public ProductDto add(ProductDto student) {
        return mapper.toDto(productRepository.save(mapper.toEntity(student)));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
