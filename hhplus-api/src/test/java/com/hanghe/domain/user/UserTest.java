package com.hanghe.domain.user;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("유저 잔액 정상 충전")
    void chargeBalance_success(){
        // given
        Long userId = 1L;
        String name = "테스트";
        Long amount = 1000L;
        User user = User.create(userId,name);

        // when
        user.chargeBalance(amount);

        // then
        Assertions.assertEquals(userId,user.getId());
        Assertions.assertEquals(name,user.getName());
        Assertions.assertEquals(amount,user.getBalance());
    }

    @Test
    @DisplayName("잔액 충전 : 잘못된 금액 입력 시 예외 발생")
    void chargeBalance_ShouldThrowException_WhenAmountIsInvalid() {
        // Arrange
        User user = User.create(1L, "테스트");

        // Act & Assert
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            user.chargeBalance(-1000L);
        });

        Assertions.assertEquals(ErrorCode.USER_MINUS_AMOUNT, exception.getErrorCode());
    }

    @Test
    @DisplayName("유저 잔액 정상 사용")
    void userBalance_success(){
        // given
        Long userId = 1L;
        String name = "테스트";
        Long amount = 1000L;
        User user = new User(userId,name,3000L);

        // when
        user.useBalance(amount);

        // then
        Assertions.assertEquals(userId,user.getId());
        Assertions.assertEquals(name,user.getName());
        Assertions.assertEquals(2000,user.getBalance());
    }

    @Test
    @DisplayName("유저 잔액 부족 시 예외 발생")
    void useBalance_ShouldThrowException_WhenBalanceIsInsufficient(){
        // given
        Long userId = 1L;
        String name = "테스트";
        Long amount = 2000L;
        User user = new User(userId,name,1000L);

        // when
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            user.useBalance(amount);
        });
        // then
        Assertions.assertEquals(ErrorCode.USER_MINUS_AMOUNT, exception.getErrorCode());
    }

    @Test
    @DisplayName("잔액 사용: 잘못된 금액 입력 시 예외 발생")
    void useBalance_ShouldThrowException_WhenAmountIsInvalid() {
        // given
        User user = User.create(1L, "Test User");

        // when
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            user.useBalance(-10L);
        });

        Assertions.assertEquals(ErrorCode.USER_MINUS_AMOUNT, exception.getErrorCode());
    }
}
