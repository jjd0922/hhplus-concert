package com.hanghe.domain.queue.repository;

import com.hanghe.domain.queue.entity.QueueToken;
import com.hanghe.domain.queue.entity.QueueStatus;
import com.hanghe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface QueueTokenRepository {

    QueueToken save(QueueToken queueToken);
    List<QueueToken> saveAll(List<QueueToken> queueTokens);
    QueueToken findById(Long queueTokenId);
    int countByStatus(QueueStatus status);

    QueueToken findByUserAndToken(User user, String token);

    List<QueueToken> findAllByStatusAndExpiredAt(QueueStatus status, LocalDateTime date);

    QueueToken findFirstByStatusOrderByIdAsc(QueueStatus status);

    List<QueueToken> findByUser(User user);
}
