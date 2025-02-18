package com.hanghe.interfaces.payment.controller;

import com.hanghe.application.PaymentFacade;
import com.hanghe.common.annotation.NotTokenCheck;
import com.hanghe.interfaces.payment.dto.request.PaymentChargeRequest;
import com.hanghe.interfaces.payment.dto.request.PaymentUseRequest;
import com.hanghe.interfaces.payment.dto.response.PaymentChargeResponse;
import com.hanghe.interfaces.payment.dto.response.PaymentUseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentFacade paymentFacade;

    /** 결제 충전 */
    @NotTokenCheck
    @PostMapping("/charge")
    public ResponseEntity<PaymentChargeResponse> paymentCharge(@RequestBody PaymentChargeRequest requestBody) {
        return ResponseEntity.ok(paymentFacade.charge(requestBody));
    }
    /** 결제 사용 */
    @PostMapping("/use")
    public ResponseEntity<PaymentUseResponse> paymentUse(@RequestBody PaymentUseRequest requestBody) {
        return ResponseEntity.ok(paymentFacade.use(requestBody));
    }
}
