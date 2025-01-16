package com.hanghe.domain.payment.service;

import com.hanghe.domain.payment.entity.PaymentType;
import com.hanghe.domain.payment.entity.Payment;
import com.hanghe.domain.payment.repository.PaymentRepository;
import com.hanghe.domain.payment.service.dto.PaymentChargeDTO;
import com.hanghe.domain.payment.service.dto.PaymentUseDTO;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.service.UserService;
import com.hanghe.domain.user.service.dto.UserBalanceDTO;
import com.hanghe.interfaces.payment.dto.request.PaymentChargeRequest;
import com.hanghe.interfaces.payment.dto.request.PaymentUseRequest;
import com.hanghe.interfaces.payment.dto.response.PaymentChargeResponse;
import com.hanghe.interfaces.payment.dto.response.PaymentUseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /** 유저 잔액 충전*/
    public PaymentChargeDTO charge(User user, int amount){
        Payment payment = Payment.create(user,amount,PaymentType.CHARGE);
        paymentRepository.save(payment);
        return PaymentChargeDTO.from(payment);
    }

    /** 유저 잔액 결제*/
    public PaymentUseDTO use(User user, int amount){
        if (user.getBalance() < amount) {
            throw new RuntimeException("잔액이 부족합니다.");
        }
        Payment payment = Payment.create(user,amount,PaymentType.USE);
        paymentRepository.save(payment);
        return PaymentUseDTO.from(payment);
    }
}
