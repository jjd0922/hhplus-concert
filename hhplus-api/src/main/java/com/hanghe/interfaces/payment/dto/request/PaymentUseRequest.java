package com.hanghe.interfaces.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentUseRequest(
        @JsonProperty("userId") Long userId,
        @JsonProperty("token") String token,
        @JsonProperty("reservationId") Long reservationId,
        @JsonProperty("balance") Long balance
) {

}
