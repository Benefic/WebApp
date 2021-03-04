package ru.abenefic.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.model.Product;
import ru.abenefic.spring.services.ProductService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(@RequestParam(defaultValue = "all") String search,
                                @RequestParam(defaultValue = "") String title,
                                @RequestParam(defaultValue = "0") float first,
                                @RequestParam(defaultValue = "0") float second,
                                @RequestParam(defaultValue = "0") float cost) {

        switch (search) {
            case "all" -> {
                return productService.getAll();
            }
            case "title_like" -> {
                return productService.getAllByTitleContains(title);
            }
            case "cost_between" -> {
                return productService.getAllByCostBetween(first, second);
            }
            case "cost_less" -> {
                return productService.getAllByCostIsLessThanEqual(cost);
            }
            case "cost_greater" -> {
                return productService.getAllByCostGreaterThanEqual(cost);
            }
            default -> {
                return Collections.emptyList();
            }
        }
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
