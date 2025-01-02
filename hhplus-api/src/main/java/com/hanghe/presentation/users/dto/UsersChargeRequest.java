package com.hanghe.presentation.users.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsersChargeRequest {
    Long userId;
    int balance;
}
