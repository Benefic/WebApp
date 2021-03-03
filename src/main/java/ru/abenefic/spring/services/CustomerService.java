package ru.abenefic.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.entities.Customer;
import ru.abenefic.spring.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void create(String name) {
        customerRepository.create(name);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> get(int id) {
        return Optional.ofNullable(customerRepository.get(id));
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public void update(int id, String name) {
        customerRepository.update(id, name);
    }

    public void delete(int id) {
        customerRepository.delete(id);
    }

    public int count() {
        return getAll().size();
    }


}
