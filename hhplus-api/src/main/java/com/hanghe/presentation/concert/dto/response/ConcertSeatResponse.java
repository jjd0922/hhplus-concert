package com.hanghe.presentation.concert.dto.response;

import com.hanghe.domain.seat.entity.Seat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Builder
@Getter
public class ConcertSeatResponse {

    LocalDate date;
    Map<Long,List<Long>> seatList;

    public static ConcertSeatResponse from(LocalDate date,Map<Long,List<Long>> list ) {
        return ConcertSeatResponse.builder()
                .date(date)
                .seatList(list)
                .build();
    }
}

