package com.system.dscommerce.dtos;

import java.time.Instant;

import com.system.dscommerce.entities.Payment;

public record PaymentDTO(
    Long id,
    Instant moment
) {

    public PaymentDTO(Payment p){
        this(p.getId(), p.getMoment());
    }
}
