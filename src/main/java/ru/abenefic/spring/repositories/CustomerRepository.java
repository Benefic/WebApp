package ru.abenefic.spring.repositories;


import ru.abenefic.spring.entities.Customer;

import java.util.List;

public interface CustomerRepository {
    void create(String name);

    Customer get(int id);

    List<Customer> getAll();

    void update(int id, String name);

    void delete(int id);

    void save(Customer customer);
}