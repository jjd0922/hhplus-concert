package com.hanghe.presentation.concert.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ConcertSeatRequest {
    Long userId;
    String token;
    LocalDate date;
}
