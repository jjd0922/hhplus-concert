package com.hanghe.domain.base.enums;

import com.hanghe.domain.base.EnumDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationType implements EnumDescription {
    WAIT("대기"),
    CANCEL("취소"),
    COMPLETE("완료");

    private final String description;
}
