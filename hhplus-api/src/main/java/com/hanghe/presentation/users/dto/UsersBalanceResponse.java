package com.hanghe.presentation.users.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsersBalanceResponse {
    Long userId;
    int balance;
}
