package com.hanghe.presentation.users.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsersBalanceRequest {
    Long userId;
}
