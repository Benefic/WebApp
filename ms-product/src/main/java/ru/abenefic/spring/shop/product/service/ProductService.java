package ru.abenefic.spring.shop.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.abenefic.spring.shop.core.exceptions.ResourceNotFoundException;
import ru.abenefic.spring.shop.core.model.dtos.ProductDto;
import ru.abenefic.spring.shop.product.model.Product;
import ru.abenefic.spring.shop.product.model.ProductMapper;
import ru.abenefic.spring.shop.product.repository.ProductRepository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    private final static String PRODUCT_KEY_PREFIX = "product:";
    private final static long PRODUCT_CACHE_TIME = 10;

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper mapper, RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public Page<ProductDto> getAll(Specification<Product> spec, int page, int size, Sort sort) {
        return productRepository.findAll(spec, PageRequest.of(page, size, sort)).map(mapper::toDto);
    }

    public ProductDto getById(Long id) {
        String key = PRODUCT_KEY_PREFIX + id;
        if (redisTemplate.hasKey(key)){
            String productData = redisTemplate.opsForValue().get(key);
            try {
                return objectMapper.readValue(productData, ProductDto.class);
            } catch (JsonProcessingException ignored) {
                ignored.printStackTrace();
            }
        }

        Optional<ProductDto> product = productRepository.findById(id).map(mapper::toDto);
        try {
            String productData = objectMapper.writeValueAsString(product);
            redisTemplate.opsForValue().set(key, productData, PRODUCT_CACHE_TIME, TimeUnit.MINUTES);
        } catch (JsonProcessingException ignored) {
            ignored.printStackTrace();
        }

        return product.orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", id)));
    }

    public ProductDto add(ProductDto student) {
        return mapper.toDto(productRepository.save(mapper.toEntity(student)));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
