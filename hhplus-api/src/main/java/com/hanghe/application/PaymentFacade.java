package com.hanghe.application;

import com.hanghe.domain.payment.service.PaymentService;
import com.hanghe.domain.payment.service.dto.PaymentChargeDTO;
import com.hanghe.domain.payment.service.dto.PaymentUseDTO;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.service.UserService;
import com.hanghe.interfaces.payment.dto.request.PaymentChargeRequest;
import com.hanghe.interfaces.payment.dto.request.PaymentUseRequest;
import com.hanghe.interfaces.payment.dto.response.PaymentChargeResponse;
import com.hanghe.interfaces.payment.dto.response.PaymentUseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentFacade {
    private final UserService userService;
    private final PaymentService paymentService;

    /** 유저 잔액 충전*/
    public PaymentChargeResponse charge(PaymentChargeRequest requestBody){
        User user = userService.findUser(requestBody.getUserId());
        PaymentChargeDTO dto = paymentService.charge(user,requestBody.getBalance());
        return PaymentChargeResponse.from(dto,userService.findUserBalance(user.getId()).balance());
    }

    /** 유저 잔액 사용*/
    public PaymentUseResponse use(PaymentUseRequest requestBody){
        User user = userService.findUser(requestBody.getUserId());
        PaymentUseDTO dto = paymentService.use(user,requestBody.getBalance());
        return PaymentUseResponse.from(dto,userService.findUserBalance(user.getId()).balance());
    }
}
