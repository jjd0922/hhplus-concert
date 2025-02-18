package com.hanghe.domain.queue.service;

import com.hanghe.domain.queue.entity.QueueStatus;
import com.hanghe.domain.queue.entity.QueueToken;
import com.hanghe.domain.queue.repository.QueueTokenRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueTokenService {

    private final UserService userService;
    private final QueueTokenRepository queueTokenRepository;

    /** 토큰 발급 */
    @Transactional
    public QueueToken generateToken(User user) {
        this.expireAllTokensForUser(user);
        QueueToken queueToken = QueueToken.generateToken(user);
        return queueTokenRepository.save(queueToken);
    }

    /** 토큰 검증 */
    @Transactional
    public void validateToken(Long userId, String token) {
        User user = userService.findUser(userId);
        QueueToken queueToken = queueTokenRepository.findByUserAndToken(user,token);
        queueToken.validation();
    }

    /** 유저 전체 토큰 만료 */
    @Transactional
    public void expireAllTokensForUser(User user) {
        List<QueueToken> tokens = queueTokenRepository.findByUser(user);
        for (QueueToken token : tokens) {
            token.expire();
        }
    }
    /** 만료 토큰 일괄 업데이트 */
    @Transactional
    public void updateExpiredTokens(List<QueueToken> expiredTokens) {
        if (expiredTokens == null || expiredTokens.isEmpty()) {
            return;
        }
        for (QueueToken token : expiredTokens) {
            token.expire();
        }
        queueTokenRepository.saveAll(expiredTokens);
    }

    /** 토큰 조회 */
    @Transactional(readOnly = true)
    public QueueToken findQueueToken(Long id) {
        return queueTokenRepository.findById(id);
    }

    /** 만료된 ACTIVE 토큰 조회 */
    @Transactional(readOnly = true)
    public List<QueueToken> findQueueActiveTokenExpire() {
        return queueTokenRepository.findAllByStatusAndExpiredAt(QueueStatus.ACTIVE,LocalDateTime.now());
    }

    /** 대기 토큰 입장*/
    @Transactional
    public void activeWaitingToken() {
        queueTokenRepository.findFirstByStatusOrderByIdAsc(QueueStatus.WAIT).active();
    }
}
