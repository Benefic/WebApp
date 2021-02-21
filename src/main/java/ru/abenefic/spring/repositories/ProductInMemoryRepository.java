package ru.abenefic.spring.repositories;

import org.springframework.stereotype.Component;
import ru.abenefic.spring.model.Product;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductInMemoryRepository implements ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        Random random = new Random();
        products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(Product.builder()
                    .id(i)
                    .title("Product " + i)
                    .cost((float) Math.abs(random.nextInt(100) * 1.4))
                    .build());
        }
    }

    @Override
    public void create(String title, float cost) {
        save(Product.builder()
                .id(products.size())
                .title(title)
                .cost(cost)
                .build()
        );
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product get(int id)  {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        products.sort(Comparator.comparingInt(Product::getId));
        return Collections.unmodifiableList(products);
    }

    @Override
    public void update(int id, String title, float cost) {
        Product product = get(id);
        if (product != null) {
            product.setTitle(title);
            product.setCost(cost);
        }
    }

    @Override
    public void delete(int id) {
        products.removeIf(product -> product.getId() == id);
    }
}
