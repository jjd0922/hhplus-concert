package com.hanghe.interfaces.queueToken.scheduler;

import com.hanghe.domain.queue.service.QueueTokenService;
import com.hanghe.domain.queue.entity.QueueToken;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QueueTokenScheduler {

    private final QueueTokenService queueTokenService;

    @Scheduled(fixedDelay = 2000)
    public void queueTokenValidation() {
        try {
            List<QueueToken> expiredTokens = queueTokenService.findQueueActiveTokenExpire();
            if (!expiredTokens.isEmpty()) {
                expiredTokens.forEach(QueueToken::expire);
                queueTokenService.updateExpiredTokens(expiredTokens);
                queueTokenService.activeWaitingToken();
//                for (QueueToken expiredToken : expiredTokens) {
//                    // 만료처리
//                    expiredToken.expire();
//                    // 대기 -> 입장
//                    queueTokenService.activeWaitingToken();
//                }
            }
        } catch (Exception e) {

        }

    }
}
