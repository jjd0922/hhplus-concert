package com.hanghe.interfaces.payment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentChargeRequest {
    Long userId;
    int balance;
}
