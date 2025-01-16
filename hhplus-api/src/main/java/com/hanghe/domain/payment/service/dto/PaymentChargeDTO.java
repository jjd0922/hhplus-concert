package com.hanghe.domain.payment.service.dto;

import com.hanghe.domain.payment.entity.Payment;

public record PaymentChargeDTO(Long paymentId, int amount) {
    public static PaymentChargeDTO from(Payment payment) {
        return new PaymentChargeDTO(
                payment.getId(),
                payment.getAmount()
        );
    }
}
