package ru.abenefic.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.model.dtos.ProductDto;
import ru.abenefic.spring.services.ShoppingCartService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<Map<ProductDto, Integer>> getCart() {
        return ResponseEntity.ok(shoppingCartService.getProducts());
    }

    @PutMapping()
    public ResponseEntity<Map<ProductDto, Integer>> addProduct(@RequestParam Long productId) {
        shoppingCartService.addProduct(productId);
        return ResponseEntity.ok(shoppingCartService.getProducts());
    }

    @DeleteMapping
    public ResponseEntity<Map<ProductDto, Integer>> removeProduct(@RequestParam Long productId) {
        shoppingCartService.removeProduct(productId);
        return ResponseEntity.ok(shoppingCartService.getProducts());
    }


}
