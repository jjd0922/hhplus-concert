package com.hanghe.presentation.users.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Getter
public class UsersTokenResponse {
    Long userId;
    UUID token;
    String status;
    int leftCount;
    LocalDateTime expiredAt;
    LocalDateTime createdAt;
}
