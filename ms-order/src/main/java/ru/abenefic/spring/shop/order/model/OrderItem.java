package ru.abenefic.spring.shop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_id")
    private long productId;
    private String productTitle;
    private float count;
    private float cost;
    private float sum;

    public OrderItem(CartItem cartItem) {
        this.productId = cartItem.getProductId();
        this.productTitle = cartItem.getProductTitle();
        this.cost = cartItem.getCost();
        this.count = cartItem.getCount();
        this.sum = cartItem.getSum();
    }
}
