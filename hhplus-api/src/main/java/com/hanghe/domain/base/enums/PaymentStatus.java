package com.hanghe.domain.base.enums;

import com.hanghe.domain.base.EnumDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus implements EnumDescription {

    FAIL("실패"),
    SUCCESS("성공");

    private final String description;
}
