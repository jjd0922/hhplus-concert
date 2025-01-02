package com.hanghe.presentation.payment.controller;

import com.hanghe.presentation.concert.dto.ConcertDateRequest;
import com.hanghe.presentation.concert.dto.ConcertDateResponse;
import com.hanghe.presentation.payment.dto.PaymentRequest;
import com.hanghe.presentation.payment.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    /** 결제 요청*/
    @PostMapping("")
    public ResponseEntity<PaymentResponse> possibleDateCheck(@RequestBody PaymentRequest requestBody) {
        return ResponseEntity.ok(PaymentResponse.builder().balance(1000).seatId(19L).userId(2L).token("11102").build());
    }
}
