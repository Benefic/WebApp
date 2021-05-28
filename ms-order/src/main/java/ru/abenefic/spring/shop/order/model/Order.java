package ru.abenefic.spring.shop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    private float summ;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems;

}
