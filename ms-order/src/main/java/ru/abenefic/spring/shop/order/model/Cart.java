package ru.abenefic.spring.shop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;
    private long userId;

    private float summ;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public void add(CartItem cartItem) {

        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                item.increment(cartItem.getCount());
                recalculate();
                return;
            }
        }
        if (cartItem != null) {
            cartItem.setCart(this);
            cartItems.add(cartItem);
        }
        recalculate();
    }

    public CartItem getItemByProductId(Long productId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProductId() == productId) {
                return cartItem;
            }
        }
        return null;
    }

    public void recalculate() {
        summ = 0;
        for (CartItem cartItem : cartItems) {
            summ += cartItem.getCost();
        }
    }

    public void clear() {
        for (CartItem cartItem : cartItems) {
            cartItem.setCart(null);
        }
        cartItems.clear();
        recalculate();
    }

    public void merge(@NotNull Cart cart) {
        for (CartItem cartItem : cart.cartItems) {
            add(cartItem);
        }
    }
}
