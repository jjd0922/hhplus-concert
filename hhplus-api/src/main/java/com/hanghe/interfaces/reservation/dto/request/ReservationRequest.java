package com.hanghe.interfaces.reservation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReservationRequest {
    Long userId;
    String token;
    Long seatId;
}
