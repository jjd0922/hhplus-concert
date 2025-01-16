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
@Transactional
public class QueueTokenService {

    private final UserService userService;
    private final QueueTokenRepository queueTokenRepository;

    /** 토큰 발급 */
    public QueueToken generateToken(User user) {
        QueueToken queueToken = QueueToken.generateToken(user);
        return queueTokenRepository.save(queueToken);
    }

    /** 토큰 검증 */
    public void validateToken(Long userId, String token) {
        User user = userService.findUser(userId);
        QueueToken queueToken = queueTokenRepository.findByUserAndToken(user,token);
        queueToken.validation();
    }

    /** 토큰 조회 */
    public QueueToken findQueueToken(Long id) {
        return queueTokenRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 토큰이 없습니다."));
    }

    /** 만료된 ACTIVE 토큰 조회 */
    public List<QueueToken> findQueueActiveTokenExpire() {
        return queueTokenRepository.findAllByStatusAndExpiredAt(QueueStatus.ACTIVE,LocalDateTime.now());
    }

    /** 대기 토큰 입장*/
    public void activeWaitingToken() {
        queueTokenRepository.findFirstByStatusOrderByIdAsc(QueueStatus.WAIT).acticeToken();
    }
}
