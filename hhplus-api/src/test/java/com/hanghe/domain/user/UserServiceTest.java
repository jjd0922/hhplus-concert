package com.hanghe.domain.user;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import com.hanghe.domain.user.service.UserService;
import com.hanghe.domain.user.service.dto.UserBalanceDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 정상적으로 조회")
    void findUser_ShouldReturnUser_WhenUserExists() {
        // given
        Long userId = 1L;
        User mockUser = User.builder().id(userId).name("테스트 유저").balance(1000L).build();
        when(userRepository.findById(userId)).thenReturn(mockUser);

        // when
        User result = userService.findUser(userId);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(userId, result.getId());
        Assertions.assertEquals("테스트 유저", result.getName());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("유저 조회 - 유저가 없을 경우 예외 발생")
    void findUser_ShouldThrowException_WhenUserNotFound() {
        // given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenThrow(new BusinessException(ErrorCode.USER_NOT_FOUND));

        // when
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            userService.findUser(userId);
        });
        // then
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("유저 잔액을 정상적으로 조회")
    void findUserBalance_ShouldReturnUserBalance_WhenUserExists() {
        // given
        Long userId = 1L;
        User mockUser = User.builder().id(userId).balance(500L).build();
        when(userRepository.findById(userId)).thenReturn(mockUser);

        // when
        UserBalanceDTO result = userService.findUserBalance(userId);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(500, result.balance());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("유저 잔액 조회 - 유저가 없을 경우 예외 발생")
    void findUserBalance_ShouldThrowException_WhenUserNotFound() {
        // given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenThrow(new BusinessException(ErrorCode.USER_NOT_FOUND));

        // when
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            userService.findUser(userId);
        });

        // then
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
        verify(userRepository, times(1)).findById(userId);
    }
}
