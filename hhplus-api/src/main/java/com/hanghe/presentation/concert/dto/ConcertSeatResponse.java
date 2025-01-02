package com.hanghe.presentation.concert.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ConcertSeatResponse {
    Long userId;
    String token;
    List<Long> seatList;
}
