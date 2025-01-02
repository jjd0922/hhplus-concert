package com.hanghe.presentation.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentRequest {
    Long userId;
    String token;
    Long seatId;
}
