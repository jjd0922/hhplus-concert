package com.hanghe.domain.reservation.entity;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class ReservationTime {
    private final LocalDateTime reservedAt;
    private final LocalDateTime expiredAt;

    public ReservationTime(LocalDateTime reservedAt, Duration duration) {
        this.reservedAt = reservedAt;
        this.expiredAt = reservedAt.plus(duration);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiredAt);
    }
}
