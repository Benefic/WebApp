package ru.abenefic.spring.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.entities.Product;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
@Primary
public class ProductDatabaseRepository implements ProductRepository {

    private final SessionFactory factory;

    @Autowired
    public ProductDatabaseRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
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
