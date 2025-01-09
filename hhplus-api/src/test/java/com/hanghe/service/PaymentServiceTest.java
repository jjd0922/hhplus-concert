package com.hanghe.service;

import com.hanghe.apllication.service.PaymentService;
import com.hanghe.apllication.service.UserService;
import com.hanghe.domain.payment.repository.PaymentRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.presentation.payment.dto.request.PaymentUseRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    @DisplayName("결제 실패 테스트 - 잔액 부족")
    void usePaymentFailTest() {
        // given
        Long userId = 1L;
        int balanceToUse = 5000; // 결제하려는 금액

        User user = User.builder()
                .id(userId)
                .balance(3000) // 잔액 부족
                .build();

        PaymentUseRequest request = PaymentUseRequest.builder()
                .userId(userId)
                .balance(balanceToUse)
                .build();

        when(userService.findUser(userId)).thenReturn(user);

        // when & then
        Assertions.assertThrows(RuntimeException.class,
                () -> paymentService.use(request),
                "잔액이 부족합니다."
        );
    }
}
