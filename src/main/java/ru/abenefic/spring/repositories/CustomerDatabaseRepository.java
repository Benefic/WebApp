package ru.abenefic.spring.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.entities.Customer;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
@Primary
public class CustomerDatabaseRepository implements CustomerRepository {

    private final SessionFactory factory;

    @Autowired
    public CustomerDatabaseRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public void create(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = new Customer(name);
            session.save(customer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Customer get(int id) {
        Customer customer;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            customer = session.get(Customer.class, id);
            session.getTransaction().commit();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            customers = session.createQuery("from Customer", Customer.class).getResultList();
            session.getTransaction().commit();
        }
        return customers;
    }

    @Override
    public void update(int id, String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                customer.setName(name);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.delete(customer);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(Customer customer) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        }
    }
}
