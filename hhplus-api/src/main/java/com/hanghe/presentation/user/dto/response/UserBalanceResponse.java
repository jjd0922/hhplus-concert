package com.hanghe.presentation.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserBalanceResponse {
    Long userId;
    int balance;

    public static UserBalanceResponse from(Long userId, int balance) {
        return UserBalanceResponse.builder()
                .userId(userId)
                .balance(balance)
                .build();
    }
}
