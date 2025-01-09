package com.hanghe.presentation.payment.controller;

import com.hanghe.apllication.service.PaymentService;
import com.hanghe.presentation.payment.dto.request.PaymentChargeRequest;
import com.hanghe.presentation.payment.dto.request.PaymentUseRequest;
import com.hanghe.presentation.payment.dto.response.PaymentChargeResponse;
import com.hanghe.presentation.payment.dto.response.PaymentUseResponse;
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

    private final PaymentService paymentService;

    /** 결제 충전 */
    @PostMapping("/charge")
    public ResponseEntity<PaymentChargeResponse> paymentCharge(@RequestBody PaymentChargeRequest requestBody) {
        return ResponseEntity.ok(paymentService.charge(requestBody));
    }
    /** 결제 사용 */
    @PostMapping("/use")
    public ResponseEntity<PaymentUseResponse> paymentUse(@RequestBody PaymentUseRequest requestBody) {
        return ResponseEntity.ok(paymentService.use(requestBody));
    }
}
