package com.hanghe.presentation.payment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentUseRequest {
    Long userId;
    String token;
    Long seatId;
    int balance;
}
