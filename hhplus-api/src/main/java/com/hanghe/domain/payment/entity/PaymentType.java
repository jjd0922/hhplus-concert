package com.hanghe.domain.payment.entity;

import com.hanghe.domain.base.EnumDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType implements EnumDescription {

    CHARGE("충전"),
    USE("사용");

    private final String description;
}
