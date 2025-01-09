package com.hanghe.presentation.payment.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentUseResponse {

    Long userId;
    Long seatId;
    int balance;

    public static PaymentUseResponse from(Long userId, int balance) {
        return PaymentUseResponse.builder()
                .userId(userId)
                .balance(balance)
                .build();
    }
}
