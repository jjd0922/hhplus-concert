package com.hanghe.interfaces.concert.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConcertDateRequest(
        @JsonProperty("concertId") Long concertId
) {

}
