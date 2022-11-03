package com.cydeo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "balance")
public class Balance extends BaseEntity{

    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;



}

