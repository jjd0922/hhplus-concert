package com.hanghe.domain.user;

import com.hanghe.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserUnitTest {

    @Test
    @DisplayName("유저 잔액 정상 충전")
    void chargeBalance_success(){
        // given
        Long userId = 1L;
        String name = "테스트";
        int amount = 1000;
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
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.chargeBalance(-1000);
        });

        Assertions.assertEquals("충전 금액은 0보다 커야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("유저 잔액 정상 사용")
    void userBalance_success(){
        // given
        Long userId = 1L;
        String name = "테스트";
        int amount = 1000;
        User user = new User(userId,name,3000);

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
        int amount = 2000;
        User user = new User(userId,name,1000);

        // when
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            user.useBalance(amount);
        });

        // then
        Assertions.assertEquals("잔액이 부족합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("잔액 사용: 잘못된 금액 입력 시 예외 발생")
    void useBalance_ShouldThrowException_WhenAmountIsInvalid() {
        // Arrange
        User user = User.create(1L, "Test User");

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.useBalance(-10);
        });

        Assertions.assertEquals("사용 금액은 0보다 커야 합니다.", exception.getMessage());
    }
}
