package com.hanghe.domain.queueToken.repository;

import com.hanghe.domain.base.enums.QueueStatus;
import com.hanghe.domain.queueToken.entity.QueueToken;
import com.hanghe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QueueTokenRepository extends JpaRepository<QueueToken,Long> {

    /** 상태값에 따른 대기열 count */
    int countByStatus(QueueStatus status);

    QueueToken findByUserAndToken(User user, String token);

    List<QueueToken> findAllByStatusAndExpiredAt(QueueStatus status, LocalDateTime date);

    QueueToken findFirstByStatusOrderByIdAsc(QueueStatus status);
}
