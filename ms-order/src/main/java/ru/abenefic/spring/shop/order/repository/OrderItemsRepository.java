package ru.abenefic.spring.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abenefic.spring.shop.order.model.Order;
import ru.abenefic.spring.shop.order.model.OrderItem;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrder(Order cart);
}
