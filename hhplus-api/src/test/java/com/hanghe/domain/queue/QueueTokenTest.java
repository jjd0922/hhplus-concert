package com.hanghe.domain.queue;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.queue.entity.QueueStatus;
import com.hanghe.domain.queue.entity.QueueToken;
import com.hanghe.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;

public class QueueTokenTest {

    @Test
    @DisplayName("토큰 정상 생성")
    void generateToken_ShouldCreateToken_WhenValidUser() {
        // Given
        User mockUser = mock(User.class);

        // When
        QueueToken token = QueueToken.generateToken(mockUser);

        // Then
        Assertions.assertNotNull(token);
        Assertions.assertEquals(mockUser, token.getUser());
        Assertions.assertNotNull(token.getToken());
        Assertions.assertNotNull(token.getExpiredAt());
        Assertions.assertTrue(token.getExpiredAt().isAfter(LocalDateTime.now()));
    }

    @Test
    @DisplayName("토큰 검증 - EXPIRED 상태 예외 발생")
    void validation_ShouldThrowException_WhenTokenIsExpired() {
        // Given
        QueueToken token = QueueToken.builder()
                .status(QueueStatus.EXPIRED)
                .build();

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            token.validation();
        });
        Assertions.assertEquals(ErrorCode.TOKEN_EXPIRED, exception.getErrorCode());
    }

    @Test
    @DisplayName("토큰 검증 - WAIT 상태 예외 발생")
    void validation_ShouldThrowException_WhenTokenIsInWaitStatus() {
        // Given
        QueueToken token = QueueToken.builder()
                .status(QueueStatus.WAIT)
                .build();

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            token.validation();
        });
        Assertions.assertEquals(ErrorCode.TOKEN_WAIT, exception.getErrorCode());
    }

    @Test
    @DisplayName("토큰 검증 - ACTIVE 상태 정상 처리")
    void validation_ShouldPass_WhenTokenIsActive() {
        // Given
        QueueToken token = QueueToken.builder()
                .status(QueueStatus.ACTIVE)
                .build();

        // When & Then
        Assertions.assertDoesNotThrow(token::validation);
    }

    @Test
    @DisplayName("토큰 상태 활성화")
    void active_ShouldUpdateStatusToActive_AndExtendExpiry() {
        // Given
        QueueToken token = QueueToken.builder()
                .status(QueueStatus.WAIT)
                .expiredAt(LocalDateTime.now())
                .build();

        // When
        token.active();

        // Then
        Assertions.assertEquals(QueueStatus.ACTIVE, token.getStatus());
        Assertions.assertNotNull(token.getExpiredAt());
        Assertions.assertTrue(token.getExpiredAt().isAfter(LocalDateTime.now()));
    }

    @Test
    @DisplayName("토큰 상태 만료 처리")
    void expire_ShouldUpdateStatusToExpired() {
        // Given
        QueueToken token = QueueToken.builder()
                .status(QueueStatus.ACTIVE)
                .build();

        // When
        token.expire();

        // Then
        Assertions.assertEquals(QueueStatus.EXPIRED, token.getStatus());
    }
}
