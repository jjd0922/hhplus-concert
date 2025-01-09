package com.hanghe.presentation.queueToken.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class QueueTokenValidResponse {
    Long userId;
    String status;
    int leftCount;
    LocalDateTime expiredAt;

    public static QueueTokenValidResponse from(Long userId, String status, int left, LocalDateTime expiredAt) {
        return QueueTokenValidResponse.builder()
                .userId(userId)
                .status(status)
                .leftCount(left)
                .expiredAt(expiredAt)
                .build();
    }
}
