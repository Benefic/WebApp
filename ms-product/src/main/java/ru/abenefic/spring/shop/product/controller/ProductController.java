package ru.abenefic.spring.shop.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.shop.core.enums.ProductSort;
import ru.abenefic.spring.shop.core.enums.SortDirection;
import ru.abenefic.spring.shop.core.exceptions.NoSuchPageException;
import ru.abenefic.spring.shop.core.model.dtos.ProductDto;
import ru.abenefic.spring.shop.product.model.Product;
import ru.abenefic.spring.shop.product.repository.ProductSpecifications;
import ru.abenefic.spring.shop.product.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAll(@RequestParam MultiValueMap<String, String> params,
                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "ASC") SortDirection costSortDirection,
                                   @RequestParam(defaultValue = "ASC") SortDirection titleSortDirection,
                                   @RequestParam(defaultValue = "TITLE") ProductSort mainSort
    ) {

        if (page <= 1) {
            page = 0;
        } else {
            page--;
        }

        Sort.Order costSorting = new Sort.Order(Sort.Direction.valueOf(costSortDirection.name()), "cost");
        Sort.Order titleSorting = new Sort.Order(Sort.Direction.valueOf(titleSortDirection.name()), "title");

        Sort resultSort;
        if (mainSort == ProductSort.COST) {
            resultSort = Sort.by(costSorting, titleSorting);
        } else {
            resultSort = Sort.by(titleSorting, costSorting);
        }

        Specification<Product> spec = ProductSpecifications.build(params);

        Page<ProductDto> products = productService.getAll(spec, page, size, resultSort);

        if (products.getTotalPages() <= page) {
            throw new NoSuchPageException("Maximum page is " + products.getTotalPages());
        }
        return products;
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto add(@RequestBody ProductDto product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
