package com.hanghe.presentation.concert.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConcertSeatReservationRequest {
    Long userId;
    String token;
    Long seatId;
}
