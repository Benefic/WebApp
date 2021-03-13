package ru.abenefic.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.abenefic.spring.model.dtos.ProductDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService {

    private final Map<ProductDto, Integer> products = new HashMap<>();
    @Autowired
    private ProductService productService;

    public void addProduct(Long productId) {
        ProductDto product = productService.getById(productId);
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void removeProduct(Long productId) {
        ProductDto product = productService.getById(productId);
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    public Map<ProductDto, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

}
