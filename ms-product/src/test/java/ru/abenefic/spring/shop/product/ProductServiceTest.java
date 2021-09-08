package ru.abenefic.spring.shop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import ru.abenefic.spring.shop.core.model.dtos.ProductDto;
import ru.abenefic.spring.shop.product.model.Product;
import ru.abenefic.spring.shop.product.model.ProductMapper;
import ru.abenefic.spring.shop.product.repository.ProductRepository;
import ru.abenefic.spring.shop.product.repository.ProductSpecifications;
import ru.abenefic.spring.shop.product.service.ProductService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = {ProductService.class})
public class ProductServiceTest {

    private final ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private RedisTemplate<String, String> redisTemplate;

    @MockBean
    private ObjectMapper objectMapper;

    public ProductServiceTest(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @Test
    void getAll() {

        Product testProduct = new Product();
        ProductDto testProductDto = new ProductDto();
        testProductDto.setTitle("Test");

        Specification<Product> spec = ProductSpecifications.build(new LinkedMultiValueMap<>());

        Mockito.doReturn(
                        new PageImpl<>(List.of(testProduct)))
                .when(productRepository)
                .findAll(spec, PageRequest.of(0, 10, Sort.unsorted()));

        Mockito.doReturn(
                        testProductDto)
                .when(productMapper)
                .toDto(testProduct);

        Page<ProductDto> products = productService.getAll(spec, 0, 10, Sort.unsorted());

        assertEquals(1, products.getContent().size());
        assertEquals("Test", products.getContent().get(0).getTitle());
    }

    @Test
    void getById() {
        Product testProduct = new Product();
        ProductDto testProductDto = new ProductDto();
        testProductDto.setTitle("Test");

        Mockito.doReturn(false)
                .when(redisTemplate)
                .hasKey("product:10");

        Mockito.doReturn(Optional.of(testProduct))
                .when(productRepository)
                .findById(10L);

        Mockito.doReturn(testProductDto)
                .when(productMapper)
                .toDto(testProduct);

        assertEquals("Test", testProductDto.getTitle());
    }

    @Test
    void add() {
        Product product = new Product();
        product.setCost(999);
        product.setTitle("Test");

        ProductDto productDto = new ProductDto();
        productDto.setId(6L);
        productDto.setCategoryId(1L);
        productDto.setCost(999);
        productDto.setTitle("Test");

        Mockito.doReturn(product)
                .when(productRepository)
                .save(product);

        Mockito.doReturn(productDto)
                .when(productMapper)
                .toDto(product);

        Mockito.doReturn(product)
                .when(productMapper)
                .toEntity(productDto);

        ProductDto result = productService.add(productDto);
        assertEquals(6L, result.getId());
        assertEquals("Test", result.getTitle());
    }


}
