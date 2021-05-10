package ru.abenefic.spring.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abenefic.spring.shop.product.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
