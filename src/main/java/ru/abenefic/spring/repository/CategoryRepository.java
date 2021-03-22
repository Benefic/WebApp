package ru.abenefic.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abenefic.spring.model.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
