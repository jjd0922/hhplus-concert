package com.hanghe.domain.payment;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.payment.entity.Payment;
import com.hanghe.domain.payment.entity.PaymentType;
import com.hanghe.domain.payment.repository.PaymentRepository;
import com.hanghe.domain.payment.service.PaymentService;
import com.hanghe.domain.payment.service.dto.PaymentChargeDTO;
import com.hanghe.domain.payment.service.dto.PaymentUseDTO;
import com.hanghe.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    @DisplayName("잔액 충전 정상 요청")
    void charge_ShouldSucceed_WhenValidRequest() {
        // Given
        User mockUser = mock(User.class);
        Long amount = 100L;
        Payment mockPayment = mock(Payment.class);

        try (MockedStatic<Payment> mockedPayment = mockStatic(Payment.class)) {
            mockedPayment.when(() -> Payment.create(mockUser, amount, PaymentType.CHARGE)).thenReturn(mockPayment);
            when(paymentRepository.save(mockPayment)).thenReturn(mockPayment);

            // When
            PaymentChargeDTO result = paymentService.charge(mockUser, amount);

            // Then
            Assertions.assertNotNull(result);
            verify(paymentRepository, times(1)).save(mockPayment);
        }
    }

    @Test
    @DisplayName("잔액 결제 정상 요청")
    void use_ShouldSucceed_WhenValidRequest() {
        // Given
        User mockUser = mock(User.class);
        Long amount = 50L;
        Payment mockPayment = mock(Payment.class);
        when(mockUser.getBalance()).thenReturn(1000L);

        try (MockedStatic<Payment> mockedPayment = mockStatic(Payment.class)) {
            mockedPayment.when(() -> Payment.create(mockUser, amount, PaymentType.USE)).thenReturn(mockPayment);
            when(paymentRepository.save(mockPayment)).thenReturn(mockPayment);

            // When
            PaymentUseDTO result = paymentService.use(mockUser, amount);

            // Then
            Assertions.assertNotNull(result);
            verify(paymentRepository, times(1)).save(mockPayment);
        }
    }

    @Test
    @DisplayName("잔액 결제 - 잔액 부족 시 예외 발생")
    void use_ShouldThrowException_WhenInsufficientBalance() {
        // Given
        User mockUser = mock(User.class);
        Long amount = 200L;
        when(mockUser.getBalance()).thenReturn(100L);

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            paymentService.use(mockUser, amount);
        });
        Assertions.assertEquals(ErrorCode.PAYMENT_INSUFFICIENT_BALANCE, exception.getErrorCode());
        verify(paymentRepository, times(0)).save(any());
    }
}
