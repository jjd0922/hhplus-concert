package com.hanghe.interfaces.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanghe.domain.payment.service.dto.PaymentChargeDTO;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentChargeResponse(
        @JsonProperty("paymentId") Long paymentId,
        @JsonProperty("amount") Long amount,
        @JsonProperty("balance") Long balance
) {
    public static PaymentChargeResponse from(PaymentChargeDTO dto, Long balance) {
        return new PaymentChargeResponse(
                dto.paymentId(),
                dto.amount(),
                balance
        );
    }
}

