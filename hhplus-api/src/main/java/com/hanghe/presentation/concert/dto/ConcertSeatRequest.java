package com.hanghe.presentation.concert.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConcertSeatRequest {
    Long userId;
    String token;
    String date;
}
