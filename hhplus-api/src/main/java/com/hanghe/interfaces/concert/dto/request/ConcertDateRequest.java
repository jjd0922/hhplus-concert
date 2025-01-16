package com.hanghe.interfaces.concert.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConcertDateRequest {
    private final Long userId;
    private final String token;
}
