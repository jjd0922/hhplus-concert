package com.hanghe.interfaces.payment.dto.response;

import com.hanghe.domain.payment.service.dto.PaymentChargeDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentChargeResponse {

    Long paymentId;
    int amount;
    int balance;

    public static PaymentChargeResponse from(PaymentChargeDTO dto, int balance) {
        return PaymentChargeResponse.builder()
                .paymentId(dto.paymentId())
                .amount(dto.amount())
                .balance(balance)
                .build();
    }
}
