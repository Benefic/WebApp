package ru.abenefic.spring.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.abenefic.spring.HiberApplication;
import ru.abenefic.spring.entities.Customer;
import ru.abenefic.spring.entities.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HiberApplication.class)
public class CustomersProductsServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomersProductsService customersProductsService;

    public CustomersProductsServiceTest() {

    }

    @Test
    public void getCustomersProducts() {
        for (Customer customer : customerService.getAll()) {
            System.out.println("Customer:");
            System.out.println(customer);
            System.out.println("Products:");
            for (Product product : customersProductsService.getCustomersProducts(customer.getId())) {
                System.out.println(product);
            }
        }
    }

    @Test
    public void getProductCustomers() {
        for (Product product : productService.getAll()) {
            System.out.println("Product:");
            System.out.println(product);
            System.out.println("Customers:");
            for (Customer customer : customersProductsService.getProductCustomers(product.getId())) {
                System.out.println(customer);
            }
        }
    }

    @Test
    public void getLastBuyCost() {
        System.out.println(customersProductsService.getLastBuyCost(1, 1));
    }
}
