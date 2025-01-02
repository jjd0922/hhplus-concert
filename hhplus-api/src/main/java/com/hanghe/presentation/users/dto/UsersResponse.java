package com.hanghe.presentation.users.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Getter
public class UsersResponse {
    Long userId;
    UUID token;
    String status;
    LocalDateTime expiredAt;
    LocalDateTime createdAt;
}
