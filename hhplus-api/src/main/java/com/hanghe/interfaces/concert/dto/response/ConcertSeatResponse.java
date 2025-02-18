package com.hanghe.interfaces.concert.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;

public class ConcertSeatResponse {

    @JsonProperty("seatId")
    private Long seatId;
    @JsonProperty("seatNo")
    private Long seatNo;
    @JsonProperty("price")
    private Long price;

    public ConcertSeatResponse(Long seatId,Long seatNo,Long price){
        this.seatId = seatId;
        this.seatNo = seatNo;
        this.price = price;
    }

    public Long getSeatId(){return seatId;}
    public Long getSeatNo(){return seatNo;}
    public Long getPrice(){return price;}
    public static ConcertSeatResponse from(ConcertSeatDTO dto) {
        return new ConcertSeatResponse(dto.seatId(),dto.seatNo(),dto.price());
    }
}

