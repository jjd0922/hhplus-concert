package com.hanghe.interfaces.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanghe.domain.payment.service.dto.PaymentUseDTO;


public class PaymentUseResponse {

    @JsonProperty("paymentId")
    private Long paymentId;
    @JsonProperty("amount")
    private Long amount;
    @JsonProperty("balance")
    private Long balance;

    public PaymentUseResponse(Long paymentId, Long amount, Long balance) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.balance = balance;
    }

    public static PaymentUseResponse from(PaymentUseDTO dto, Long balance) {
        return new PaymentUseResponse(dto.paymentId(),dto.amount(),balance);
    }
}
