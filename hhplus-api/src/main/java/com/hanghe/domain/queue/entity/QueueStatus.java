package com.hanghe.domain.queue.entity;

import com.hanghe.domain.base.EnumDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueueStatus implements EnumDescription {
    WAIT("대기"),
    ACTIVE("활성"),
    EXPIRED("만료");

    private final String description;
}
