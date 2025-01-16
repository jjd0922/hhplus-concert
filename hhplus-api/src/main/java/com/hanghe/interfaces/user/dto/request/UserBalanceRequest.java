package com.hanghe.interfaces.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserBalanceRequest {
    Long userId;
}
