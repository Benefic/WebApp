package ru.abenefic.spring.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abenefic.spring.shop.order.model.Order;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    Order findByUserIdAndId(long userId, long orderId);
}
