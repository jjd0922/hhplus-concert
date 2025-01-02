package com.hanghe.presentation.concert.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class ConcertDateResponse {
    private List<LocalDate> possibleDate;
}
