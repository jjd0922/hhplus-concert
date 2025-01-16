package com.hanghe.interfaces.concert.dto.response;

import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class ConcertSeatResponse {

    private Long seatId;
    private int seatNo;
    private int price;

    public static ConcertSeatResponse from(ConcertSeatDTO dto) {
        return ConcertSeatResponse.builder()
                .seatId(dto.id())
                .seatNo(dto.seatNo())
                .price(dto.price())
                .build();
    }
}

