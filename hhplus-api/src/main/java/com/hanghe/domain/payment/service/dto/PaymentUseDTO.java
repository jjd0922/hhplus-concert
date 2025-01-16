package com.hanghe.domain.payment.service.dto;

import com.hanghe.domain.payment.entity.Payment;

public record PaymentUseDTO(Long paymentId, int amount) {
    public static PaymentUseDTO from(Payment payment) {
        return new PaymentUseDTO(
                payment.getId(),
                payment.getAmount()
        );
    }
}
