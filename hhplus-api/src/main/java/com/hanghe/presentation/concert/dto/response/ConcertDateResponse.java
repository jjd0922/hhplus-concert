package com.hanghe.presentation.concert.dto.response;

import com.hanghe.domain.seat.entity.SeatReservation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
public class ConcertDateResponse {
    private Set<LocalDate> possibleDate;

    public static ConcertDateResponse from(Set<LocalDate> date) {
        return ConcertDateResponse.builder()
                .possibleDate(date)
                .build();
    }
}
