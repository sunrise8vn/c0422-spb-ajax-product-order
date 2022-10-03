package com.cg.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;


    @OneToOne
    private User user;


    @OneToMany
    private List<CartItem> cartItems;

    public Bill toBill() {
        return new Bill()
                .setId(0L)
                .setTotalAmount(totalAmount)
                .setUser(user);
    }

}
