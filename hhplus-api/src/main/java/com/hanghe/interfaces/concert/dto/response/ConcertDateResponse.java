package com.hanghe.interfaces.concert.dto.response;

import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class ConcertDateResponse {
    private Long concertScheduleId;
    private LocalDate performanceDate;
    public static ConcertDateResponse from(ConcertScheduleDTO dto) {
        return ConcertDateResponse.builder()
                .concertScheduleId(dto.concertScheduleId())
                .performanceDate(dto.performanceDate())
                .build();
    }
}
