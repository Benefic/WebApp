package ru.abenefic.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.exceptions.NoSuchPageException;
import ru.abenefic.spring.model.Product;
import ru.abenefic.spring.model.ProductSort;
import ru.abenefic.spring.model.SortDirection;
import ru.abenefic.spring.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(@RequestParam(name = "s", defaultValue = "all") String search,
                                                @RequestParam(defaultValue = "") String title,
                                                @RequestParam(defaultValue = "0") float first,
                                                @RequestParam(defaultValue = "0") float second,
                                                @RequestParam(defaultValue = "0") float cost,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "ASC") SortDirection costSort,
                                                @RequestParam(defaultValue = "ASC") SortDirection titleSort,
                                                @RequestParam(defaultValue = "TITLE") ProductSort mainSort
    ) {

        if (page <= 1) {
            page = 0;
        } else {
            page--;
        }

        Sort.Order costSorting = new Sort.Order(Sort.Direction.valueOf(costSort.name()), "cost");
        Sort.Order titleSorting = new Sort.Order(Sort.Direction.valueOf(titleSort.name()), "title");

        Sort resultSort;
        if (mainSort == ProductSort.COST) {
            resultSort = Sort.by(costSorting, titleSorting);
        } else {
            resultSort = Sort.by(titleSorting, costSorting);
        }

        Page<Product> products;

        switch (search) {
            case "title_like" -> products = productService.getAllByTitleContains(title, page, size);
            case "cost_between" -> products = productService.getAllByCostBetween(first, second, page, size);
            case "cost_less" -> products = productService.getAllByCostIsLessThanEqual(cost, page, size);
            case "cost_greater" -> products = productService.getAllByCostGreaterThanEqual(cost, page, size);
            default -> // all
                    products = productService.getAll(page, size, resultSort);
        }
        if (products.getTotalPages() < page) {
            throw new NoSuchPageException("Maximum page is " + products.getTotalPages());
        }
        return new ResponseEntity<>(products.getContent(), HttpStatus.OK);
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
