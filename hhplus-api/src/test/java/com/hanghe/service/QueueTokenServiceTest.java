package com.hanghe.service;

import com.hanghe.apllication.service.QueueTokenService;
import com.hanghe.apllication.service.UserService;
import com.hanghe.domain.base.enums.QueueStatus;
import com.hanghe.domain.queueToken.entity.QueueToken;
import com.hanghe.domain.queueToken.repository.QueueTokenRepository;
import com.hanghe.domain.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QueueTokenServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private QueueTokenRepository queueTokenRepository;

    @InjectMocks
    private QueueTokenService queueTokenService;

    @Test
    @DisplayName("토큰 검증 성공 테스트")
    void validateTokenSuccessTest() {
        // given
        Long userId = 1L;
        String validToken = "valid-token";

        User user = User.builder().id(userId).name("테스트 유저").build();
        QueueToken queueToken = QueueToken.builder().user(user).status(QueueStatus.ACTIVE).token(validToken).build();

        when(userService.findUser(userId)).thenReturn(user);
        when(queueTokenRepository.findByUserAndToken(user, validToken)).thenReturn(queueToken);

        // when
        queueTokenService.validateToken(userId, validToken);

        // then
        verify(queueTokenRepository, times(1)).findByUserAndToken(user, validToken);
    }

    @Test
    @DisplayName("토큰 검증 실패 테스트 - 잘못된 토큰")
    void validateTokenFailTest() {
        // given
        Long userId = 1L;
        String invalidToken = "invalid-token";

        User user = User.builder().id(userId).name("테스트 유저").build();

        when(userService.findUser(userId)).thenReturn(user);
        when(queueTokenRepository.findByUserAndToken(user, invalidToken)).thenThrow(new EntityNotFoundException("유효하지 않은 토큰입니다."));

        // when & then
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> queueTokenService.validateToken(userId, invalidToken),
                "유효하지 않은 토큰입니다."
        );
    }
}
