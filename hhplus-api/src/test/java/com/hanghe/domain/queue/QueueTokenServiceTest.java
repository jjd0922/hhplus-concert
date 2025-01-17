package com.hanghe.domain.queue;

import com.hanghe.domain.queue.entity.QueueStatus;
import com.hanghe.domain.queue.entity.QueueToken;
import com.hanghe.domain.queue.repository.QueueTokenRepository;
import com.hanghe.domain.queue.service.QueueTokenService;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QueueTokenServiceTest {

    @InjectMocks
    private QueueTokenService queueTokenService;

    @Mock
    private UserService userService;

    @Mock
    private QueueTokenRepository queueTokenRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("토큰 정상 발급")
    void generateToken_ShouldGenerateAndSaveToken_WhenValidUser() {
        // Given
        User mockUser = mock(User.class);
        QueueToken mockToken = mock(QueueToken.class);
        when(queueTokenRepository.findByUser(mockUser)).thenReturn(List.of());
        when(queueTokenRepository.save(any(QueueToken.class))).thenReturn(mockToken);

        // When
        QueueToken result = queueTokenService.generateToken(mockUser);

        // Then
        Assertions.assertNotNull(result);
        verify(queueTokenRepository, times(1)).findByUser(mockUser);
        verify(queueTokenRepository, times(1)).save(any(QueueToken.class));
    }

    @Test
    @DisplayName("토큰 검증 - 정상 케이스")
    void validateToken_ShouldNotThrowException_WhenTokenIsValid() {
        // Given
        Long userId = 1L;
        String token = "valid-token";
        User mockUser = mock(User.class);
        QueueToken mockQueueToken = mock(QueueToken.class);

        when(userService.findUser(userId)).thenReturn(mockUser);
        when(queueTokenRepository.findByUserAndToken(mockUser, token)).thenReturn(mockQueueToken);

        // When
        Assertions.assertDoesNotThrow(() -> queueTokenService.validateToken(userId, token));

        // Then
        verify(queueTokenRepository, times(1)).findByUserAndToken(mockUser, token);
        verify(mockQueueToken, times(1)).validation();
    }

    @Test
    @DisplayName("유저 전체 토큰 만료")
    void expireAllTokensForUser_ShouldExpireAllTokens_WhenUserHasTokens() {
        // Given
        User mockUser = mock(User.class);
        QueueToken token1 = mock(QueueToken.class);
        QueueToken token2 = mock(QueueToken.class);
        when(queueTokenRepository.findByUser(mockUser)).thenReturn(List.of(token1, token2));

        // When
        queueTokenService.expireAllTokensForUser(mockUser);

        // Then
        verify(token1, times(1)).expire();
        verify(token2, times(1)).expire();
    }

    @Test
    @DisplayName("토큰 조회 - 존재하는 토큰")
    void findQueueToken_ShouldReturnToken_WhenTokenExists() {
        // Given
        Long tokenId = 1L;
        QueueToken mockToken = mock(QueueToken.class);
        when(queueTokenRepository.findById(tokenId)).thenReturn(Optional.of(mockToken));

        // When
        QueueToken result = queueTokenService.findQueueToken(tokenId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockToken, result);
        verify(queueTokenRepository, times(1)).findById(tokenId);
    }

    @Test
    @DisplayName("토큰 조회 - 존재하지 않는 토큰")
    void findQueueToken_ShouldThrowException_WhenTokenDoesNotExist() {
        // Given
        Long tokenId = 1L;
        when(queueTokenRepository.findById(tokenId)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            queueTokenService.findQueueToken(tokenId);
        });

        Assertions.assertEquals("해당 토큰이 없습니다.", exception.getMessage());
        verify(queueTokenRepository, times(1)).findById(tokenId);
    }

    @Test
    @DisplayName("만료된 ACTIVE 토큰 조회")
    void findQueueActiveTokenExpire_ShouldReturnTokens_WhenActiveTokensExist() {
        // Given
        QueueToken token1 = mock(QueueToken.class);
        QueueToken token2 = mock(QueueToken.class);
        LocalDateTime fixedNow = LocalDateTime.now();

        when(queueTokenRepository.findAllByStatusAndExpiredAt(eq(QueueStatus.ACTIVE), eq(fixedNow)))
                .thenReturn(List.of(token1, token2));

        try (MockedStatic<LocalDateTime> mockedTime = mockStatic(LocalDateTime.class)) {
            mockedTime.when(LocalDateTime::now).thenReturn(fixedNow);

            // When
            List<QueueToken> result = queueTokenService.findQueueActiveTokenExpire();

            // Then
            Assertions.assertNotNull(result);
            Assertions.assertEquals(2, result.size());

            verify(queueTokenRepository, times(1))
                    .findAllByStatusAndExpiredAt(eq(QueueStatus.ACTIVE), eq(fixedNow));
        }
    }

    @Test
    @DisplayName("대기 토큰 입장 - 정상 케이스")
    void activeWaitingToken_ShouldActivateFirstWaitingToken_WhenTokensExist() {
        // Given
        QueueToken mockToken = mock(QueueToken.class);
        when(queueTokenRepository.findFirstByStatusOrderByIdAsc(QueueStatus.WAIT)).thenReturn(mockToken);

        // When
        queueTokenService.activeWaitingToken();

        // Then
        verify(mockToken, times(1)).active();
    }
}
