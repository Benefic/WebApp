package ru.abenefic.spring.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shop_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
