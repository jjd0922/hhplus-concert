package com.hanghe.interfaces.payment.dto.response;

import com.hanghe.domain.payment.service.dto.PaymentUseDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentUseResponse {

    Long paymentId;
    int amount;
    int balance;

    public static PaymentUseResponse from(PaymentUseDTO dto, int balance) {
        return PaymentUseResponse.builder()
                .paymentId(dto.paymentId())
                .amount(dto.amount())
                .balance(balance)
                .build();
    }
}
