package ru.abenefic.spring.shop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.abenefic.spring.shop.core.model.dtos.ProductDto;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_id")
    private long productId;
    private String productTitle;
    private float count;
    private float cost;
    private float sum;

    public CartItem(ProductDto product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.count = 1;
        this.cost = product.getCost();
        recalc();
    }

    public void increment() {
        count++;
        recalc();
    }

    public void increment(float amount) {
        count += amount;
        recalc();
    }

    private void recalc() {
        sum = cost * count;
    }

    public void decrementQuantity() {
        count--;
        recalc();
    }

}

