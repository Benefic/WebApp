package ru.abenefic.spring.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.PrepareDataApp;
import ru.abenefic.spring.model.Product;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Primary
public class ProductDatabaseRepository implements ProductRepository {

    private static SessionFactory factory;

    @PostConstruct
    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Override
    public void create(String title, float cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product(title, cost);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Product get(int id) {
        Product product;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            products = session.createQuery("from Product", Product.class).getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    @Override
    public void update(int id, String title, float cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setTitle(title);
                product.setCost(cost);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }
}
