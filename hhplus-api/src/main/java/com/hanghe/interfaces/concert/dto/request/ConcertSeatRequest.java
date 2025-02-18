package com.hanghe.interfaces.concert.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConcertSeatRequest(
        @JsonProperty("concertScheduleId")
        Long concertScheduleId
) {

}
