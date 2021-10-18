package ru.abenefic.spring.shop.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import ru.abenefic.spring.shop.core.model.dtos.ProductDto;
import ru.abenefic.spring.shop.product.model.Product;
import ru.abenefic.spring.shop.product.repository.ProductSpecifications;
import ru.abenefic.spring.shop.product.service.ProductService;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest //(classes = {ProductService.class, ProductRepository.class})
class ProductServiceIntegrationTest {

    private final ProductService productService;

    ProductServiceIntegrationTest(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @Test
    void getAll() {
        Specification<Product> spec = ProductSpecifications.build(new LinkedMultiValueMap<>());
        Page<ProductDto> products = productService.getAll(spec, 0, 10, Sort.unsorted());

        assertEquals(5, products.getContent().size());
        assertEquals("Phone", products.getContent().get(2).getTitle());
    }

    @Test
    void getById() {
        ProductDto productDto = productService.getById(5L);
        assertEquals("Bread", productDto.getTitle());
    }

    @Test
    @Transactional
    @Rollback
    void add() {
        ProductDto productDto = new ProductDto();
        productDto.setCategoryId(1L);
        productDto.setCost(999);
        productDto.setTitle("Test");

        ProductDto result = productService.add(productDto);
        assertEquals("Test", result.getTitle());
    }

    @Test
    @Transactional
    @Rollback
    void delete() {
        productService.delete(5L);

        Specification<Product> spec = ProductSpecifications.build(new LinkedMultiValueMap<>());
        Page<ProductDto> products = productService.getAll(spec, 0, 10, Sort.unsorted());
        assertEquals(4, products.getContent().size());

    }

    @Test
    @Rollback
    void save() {
        ProductDto productDto = productService.getById(5L);
        productDto.setTitle("Test");
        productService.save(productDto);
        assertEquals("Test", productDto.getTitle());
    }
}
