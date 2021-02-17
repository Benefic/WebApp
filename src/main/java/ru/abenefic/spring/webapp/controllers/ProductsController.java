package ru.abenefic.spring.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.abenefic.spring.webapp.model.Product;
import ru.abenefic.spring.webapp.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "all_products";
    }

    @GetMapping("/remove/{id}")
    public String deleteProductById(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products/all";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }
}
