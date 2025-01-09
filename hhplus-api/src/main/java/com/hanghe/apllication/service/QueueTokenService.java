package com.hanghe.apllication.service;

import com.hanghe.domain.base.enums.QueueStatus;
import com.hanghe.domain.queueToken.entity.QueueToken;
import com.hanghe.domain.queueToken.repository.QueueTokenRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.presentation.queueToken.dto.request.QueueTokenValidRequest;
import com.hanghe.presentation.queueToken.dto.response.QueueTokenIssueResponse;
import com.hanghe.presentation.queueToken.dto.response.QueueTokenValidResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QueueTokenService {

    private final UserService userService;
    private final QueueTokenRepository queueTokenRepository;

    /** 토큰 발급 */
    public QueueTokenIssueResponse generateToken(Long userId) {
        User user = userService.findUser(userId);
        user.setUserTokenExpire(); // 기존 토큰 만료
        QueueToken queueToken = QueueToken.generateToken(user);
        queueTokenRepository.save(queueToken);
        return QueueTokenIssueResponse.from(userId, queueToken.getToken(),queueToken.getExpiredAt());
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
