package ru.abenefic.spring.shop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CartItem> cartItems;

    public void add(CartItem cartItem) {
        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                item.increment(cartItem.getCount());
                recalculate();
                return;
            }
        }
        cartItems.add(cartItem);
        cartItem.setCart(this);
        recalculate();
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

    public void merge(Cart cart) {
        for (CartItem cartItem : cart.cartItems) {
            add(cartItem);
        }
    }
}
