package com.hanghe.presentation.payment.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentChargeResponse {

    Long userId;
    int balance;

    public static PaymentChargeResponse from(Long userId, int balance) {
        return PaymentChargeResponse.builder()
                .userId(userId)
                .balance(balance)
                .build();
    }
}
