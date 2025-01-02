package com.hanghe.presentation.payment.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PaymentResponse {
    Long userId;
    String token;
    Long seatId;
    int balance;
}
