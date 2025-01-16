package com.hanghe.interfaces.queueToken.scheduler;

import com.hanghe.domain.queue.service.QueueTokenService;
import com.hanghe.domain.queue.entity.QueueToken;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
public class QueueTokenScheduler {

    private final QueueTokenService queueTokenService;

    @Scheduled(fixedDelay = 1000)
    public void queueTokenValidation() {
        try {
            List<QueueToken> expiredTokens = queueTokenService.findQueueActiveTokenExpire();
            for (QueueToken expiredToken : expiredTokens) {
                // 만료처리
                expiredToken.expireToken();
                // 대기 -> 입장
                queueTokenService.activeWaitingToken();
            }
        } catch (Exception e) {

        }

    }
}
