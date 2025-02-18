package com.hanghe.interfaces.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanghe.domain.user.service.dto.UserBalanceDTO;

public class UserBalanceResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("balance")
    private Long balance;

    public UserBalanceResponse() {}

    public UserBalanceResponse(Long id, Long balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getUserId() { return id; }
    public Long getBalance() { return balance; }

    public static UserBalanceResponse from(UserBalanceDTO dto) {
        return new UserBalanceResponse(dto.userId(), dto.balance());
    }
}
