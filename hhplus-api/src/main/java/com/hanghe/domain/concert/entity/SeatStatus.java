package com.hanghe.domain.concert.entity;

import com.hanghe.domain.base.EnumDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatStatus implements EnumDescription {

    AVAILABLE("예약가능"),
    WAITING("예약대기"),
    RESERVED("예약완료");

    private final String description;
}
