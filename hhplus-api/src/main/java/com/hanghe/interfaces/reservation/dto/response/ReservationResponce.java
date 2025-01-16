package com.hanghe.interfaces.reservation.dto.response;

import com.hanghe.domain.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReservationResponce {
    Long reservationId;
    String status;
    LocalDateTime reservedAt;
    LocalDateTime expiredAt;

    public static ReservationResponce from(Reservation reservation) {
        return ReservationResponce.builder()
                .reservationId(reservation.getId())
                .status(reservation.getStatus().toString())
                .reservedAt(reservation.getReservedAt())
                .expiredAt(reservation.getExpiredAt())
                .build();
    }
}
