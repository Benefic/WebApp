package ru.abenefic.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abenefic.spring.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCostBetween(float first, float second);

    List<Product> findProductByCostIsLessThanEqual(float cost);

    List<Product> findProductsByCostGreaterThanEqual(float cost);
}
