package com.hanghe.interfaces.user.dto.response;

import com.hanghe.domain.user.service.dto.UserBalanceDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserBalanceResponse {

    int balance;
    public static UserBalanceResponse from(UserBalanceDTO dto) {
        return UserBalanceResponse.builder()
                .balance(dto.balance())
                .build();
    }
}
