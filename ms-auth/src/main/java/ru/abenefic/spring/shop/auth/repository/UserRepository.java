package ru.abenefic.spring.shop.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.abenefic.spring.shop.auth.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String username);
}
