package com.hanghe.interfaces.queueToken.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class QueueTokenIssueResponse {
    Long userId;
    String token;
    LocalDateTime expiredAt;

    public static QueueTokenIssueResponse from(Long userId, String token, LocalDateTime expiredAt) {
        return QueueTokenIssueResponse.builder()
                .userId(userId)
                .token(token)
                .expiredAt(expiredAt)
                .build();
    }
}
