package com.hanghe.infrastructure.queue;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.queue.entity.QueueStatus;
import com.hanghe.domain.queue.entity.QueueToken;
import com.hanghe.domain.queue.repository.QueueTokenRepository;
import com.hanghe.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueueTokenRepositoryImpl implements QueueTokenRepository {

    private final QueueTokenJpaRepository queueTokenJpaRepository;


    @Override
    public QueueToken save(QueueToken queueToken) {
        return queueTokenJpaRepository.save(queueToken);
    }

    @Override
    public List<QueueToken> saveAll(List<QueueToken> queueTokens) {
        return queueTokenJpaRepository.saveAll(queueTokens);
    }

    @Override
    public QueueToken findById(Long queueTokenId) {
        return queueTokenJpaRepository.findById(queueTokenId).orElseThrow(()-> new BusinessException(ErrorCode.TOKEN_INVALID));
    }
    @Override
    public int countByStatus(QueueStatus status) {
        return queueTokenJpaRepository.countByStatus(status);
    }

    @Override
    public QueueToken findByUserAndToken(User user, String token) {
        return queueTokenJpaRepository.findByUserAndToken(user,token);
    }

    @Override
    public List<QueueToken> findAllByStatusAndExpiredAt(QueueStatus status, LocalDateTime date) {
        return queueTokenJpaRepository.findAllByStatusAndExpiredAt(status,date);
    }

    @Override
    public QueueToken findFirstByStatusOrderByIdAsc(QueueStatus status) {
        return queueTokenJpaRepository.findFirstByStatusOrderByIdAsc(status);
    }

    @Override
    public List<QueueToken> findByUser(User user) {
        return queueTokenJpaRepository.findByUser(user);
    }
}
