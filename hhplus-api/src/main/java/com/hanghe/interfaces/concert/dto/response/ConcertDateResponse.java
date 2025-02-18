package com.hanghe.interfaces.concert.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import java.time.LocalDate;

public class ConcertDateResponse {

    @JsonProperty("concertScheduleId")
    private Long concertScheduleId;

    @JsonProperty("performanceDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate performanceDate;

    public ConcertDateResponse(Long concertScheduleId, LocalDate performanceDate){
        this.concertScheduleId = concertScheduleId;
        this.performanceDate = performanceDate;
    }

    public Long getConcertScheduleId(){return concertScheduleId;}
    public LocalDate getPerformanceDate(){return performanceDate;}

    public static ConcertDateResponse from(ConcertScheduleDTO dto) {
        return new ConcertDateResponse(dto.concertScheduleId(),dto.performanceDate());
    }

}
