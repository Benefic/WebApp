package ru.abenefic.spring.shop.core.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.shop.core.enums.ProductSort;
import ru.abenefic.spring.shop.core.enums.SortDirection;
import ru.abenefic.spring.shop.core.model.dtos.ProductDto;

import java.util.List;

@FeignClient(value = "ms-product", path = "/api/v1/products")
public interface ProductClient {
    @GetMapping
    Page<ProductDto> getAll(@RequestParam MultiValueMap<String, String> params,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "ASC") SortDirection costSortDirection,
                                  @RequestParam(defaultValue = "ASC") SortDirection titleSortDirection,
                                  @RequestParam(defaultValue = "TITLE") ProductSort mainSort
    );

    @GetMapping("/{id}")
    ProductDto getById(@PathVariable Long id);

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto add(@RequestBody ProductDto product);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable Long id);
}
