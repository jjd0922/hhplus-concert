package com.hanghe.interfaces.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentChargeRequest(
        @JsonProperty("userId") Long userId,
        @JsonProperty("amount") Long amount
) {

}