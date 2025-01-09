package com.hanghe.apllication.service;

import com.hanghe.domain.base.enums.PaymentType;
import com.hanghe.domain.payment.emtity.Payment;
import com.hanghe.domain.payment.repository.PaymentRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.presentation.payment.dto.request.PaymentChargeRequest;
import com.hanghe.presentation.payment.dto.request.PaymentUseRequest;
import com.hanghe.presentation.payment.dto.response.PaymentChargeResponse;
import com.hanghe.presentation.payment.dto.response.PaymentUseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final UserService userService;
    private final PaymentRepository paymentRepository;

    /** 유저 잔액 충전*/
    public PaymentChargeResponse charge(PaymentChargeRequest requestBody){
        User user = userService.findUser(requestBody.getUserId());
        Payment payment = Payment.createPayment(user,requestBody.getBalance(),PaymentType.CHARGE);
        paymentRepository.save(payment);
        user.updateBalance();
        return PaymentChargeResponse.from(user.getId(),user.getBalance());
    }

    /** 유저 잔액 결제*/
    public PaymentUseResponse use(PaymentUseRequest requestBody){
        User user = userService.findUser(requestBody.getUserId());
        Payment payment = Payment.createPayment(user,requestBody.getBalance(),PaymentType.USE);
        paymentRepository.save(payment);
        user.updateBalance();
        return PaymentUseResponse.from(user.getId(),user.getBalance());
    }
}
