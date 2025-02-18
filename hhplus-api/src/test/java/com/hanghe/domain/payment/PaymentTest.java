package com.hanghe.domain.payment;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.payment.entity.Payment;
import com.hanghe.domain.payment.entity.PaymentType;
import com.hanghe.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PaymentTest {

    @Test
    @DisplayName("정상적으로 CHARGE 타입 Payment 생성")
    void createPayment_Charge_ShouldSucceed() {
        // given
        User mockUser = mock(User.class);
        Long amount = 1000L;

        // when
        Payment payment = Payment.create(mockUser, amount, PaymentType.CHARGE);

        // then
        Assertions.assertNotNull(payment);
        Assertions.assertEquals(amount, payment.getAmount());
        Assertions.assertEquals(PaymentType.CHARGE, payment.getType());
    }

    @Test
    @DisplayName("정상적으로 USE 타입 Payment 생성")
    void createPayment_Use_ShouldSucceed() {
        // given
        User mockUser = mock(User.class);
        Long amount = 1000L;

        // when
        Payment payment = Payment.create(mockUser, amount, PaymentType.USE);

        // then
        Assertions.assertNotNull(payment);
        Assertions.assertEquals(amount, payment.getAmount());
        Assertions.assertEquals(PaymentType.USE, payment.getType());
    }

    @Test
    @DisplayName("유효하지 않은 PaymentType으로 Payment 생성 시 예외 발생")
    void createPayment_InvalidType_ShouldThrowException() {
        // given
        User mockUser = mock(User.class);
        Long amount = 50L;

        // when
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            Payment.create(mockUser, amount, null); // 유효하지 않은 타입
        });

        // then
        Assertions.assertEquals(ErrorCode.PAYMENT_NOT_FOUND, exception.getErrorCode());

    }

    @Test
    @DisplayName("CHARGE 타입 Payment 생성 시, 유저가 null이면 예외 발생")
    void createPayment_Charge_UserNull_ShouldThrowException() {
        // given
        User nullUser = null;
        Long amount = 1000L;

        // when
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            Payment.create(nullUser, amount, PaymentType.CHARGE);
        });
        // then
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("USE 타입 Payment 생성 시, 유효하지 않은 금액으로 예외 발생")
    void createPayment_Use_InvalidAmount_ShouldThrowException() {
        // given
        User mockUser = mock(User.class);
        Long invalidAmount = -1000L;

        // when
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            Payment.create(mockUser, invalidAmount, PaymentType.USE);
        });
        // then
        Assertions.assertEquals(ErrorCode.PAYMENT_MINUS_AMOUNT, exception.getErrorCode());
    }
}
