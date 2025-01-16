package com.hanghe.domain.reservation.entity;

import com.hanghe.domain.base.EnumDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus implements EnumDescription {
    WAIT("대기"),
    CANCEL("취소"),
    COMPLETE("완료");

    private final String description;
}
