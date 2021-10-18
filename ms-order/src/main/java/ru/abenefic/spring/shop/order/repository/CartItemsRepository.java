package ru.abenefic.spring.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abenefic.spring.shop.order.model.Cart;
import ru.abenefic.spring.shop.order.model.CartItem;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);
}
