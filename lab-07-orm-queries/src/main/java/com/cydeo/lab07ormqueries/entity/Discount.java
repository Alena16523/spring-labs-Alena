package com.cydeo.lab07ormqueries.entity;

import com.cydeo.enums.DiscountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "discount")
public class Discount extends BaseEntity {

    private Integer discount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private String name;


}