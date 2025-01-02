package com.hanghe.presentation.users.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Builder
@Getter
public class UsersTokenRequest {
    Long userId;
    String token;
}
