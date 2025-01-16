package com.hanghe.interfaces.queueToken.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QueueTokenValidRequest {
    Long userId;
    String token;
}
