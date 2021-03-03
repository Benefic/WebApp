package ru.abenefic.spring.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.entities.Customer;
import ru.abenefic.spring.entities.Product;
import ru.abenefic.spring.repositories.CustomerRepository;
import ru.abenefic.spring.repositories.ProductRepository;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CustomersProductsService {

    private final SessionFactory sessionFactory;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    @Autowired
    public CustomersProductsService(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getCustomersProducts(int customerId) {
        return customerRepository.get(customerId).getProducts();
    }

    public List<Customer> getProductCustomers(int productId) {
        return productRepository.get(productId).getCustomers();
    }

    public float getLastBuyCost(int customerId, int productId) {
        float cost;
        try (Session session = sessionFactory.getCurrentSession()) {
            String query = "select buy_cost from customers_products where customer_id = " +
                    "" + customerId + " and product_id = " +
                    "" + productId + " order by buy_timestamp desc limit 1";
            session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery(query);
            cost = ((BigDecimal) nativeQuery.getSingleResult()).floatValue();
            session.getTransaction().commit();
        }
        return cost;
    }
}
