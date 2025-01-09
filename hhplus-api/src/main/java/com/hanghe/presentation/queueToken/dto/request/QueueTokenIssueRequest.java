package com.hanghe.presentation.queueToken.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QueueTokenIssueRequest {
    Long userId;
}
