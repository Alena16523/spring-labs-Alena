package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO findById(Long id);

}