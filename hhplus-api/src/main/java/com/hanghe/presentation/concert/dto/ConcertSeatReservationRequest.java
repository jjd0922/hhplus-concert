package com.hanghe.presentation.concert.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConcertSeatReservationRequest {
    Long userId;
    String token;
    Long seatId;
}
