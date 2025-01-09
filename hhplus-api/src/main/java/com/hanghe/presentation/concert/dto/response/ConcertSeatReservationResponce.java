package com.hanghe.presentation.concert.dto.response;

import com.hanghe.domain.seat.entity.SeatReservation;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConcertSeatReservationResponce {
    Long userId;
    Long seatId;
    String reservedAt;
    String expiredAt;

    public static ConcertSeatReservationResponce from(SeatReservation reservation) {
        return ConcertSeatReservationResponce.builder()
                .userId(reservation.getUser().getId())
                .seatId(reservation.getSeat().getId())
                .reservedAt(reservation.getReservedAt().toString())
                .expiredAt(reservation.getExpiredAt().toString())
                .build();
    }
}
