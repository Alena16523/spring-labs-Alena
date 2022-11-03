package com.cydeo.entity;

import com.cydeo.enums.PaymentMethod;
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
@Table(name = "payment")
public class Payment extends BaseEntity{

    private Integer paidPrice;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


}
