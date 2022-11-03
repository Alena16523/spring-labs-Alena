package com.cydeo.entity;

import com.cydeo.enums.CartState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "cart")
public class Cart extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CartState cartState;

    @OneToOne(mappedBy = "cart")
    private Order orders;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Discount discount;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItem;



}
