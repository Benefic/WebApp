package ru.abenefic.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abenefic.spring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findProductsByCostBetween(float first, float second, Pageable pageRequest);

    Page<Product> findProductsByCostIsLessThanEqual(float cost, Pageable pageRequest);

    Page<Product> findProductsByCostGreaterThanEqual(float cost, Pageable pageRequest);

    Page<Product> findProductsByTitleContainsIgnoreCase(String title, Pageable pageRequest);
}
