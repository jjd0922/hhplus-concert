package com.hanghe.presentation.concert.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConcertSeatReservationResponce {
    Long userId;
    String token;
    Long seatId;
}
