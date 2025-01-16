package com.hanghe.application;

import com.hanghe.domain.queue.entity.QueueToken;
import com.hanghe.domain.queue.service.QueueTokenService;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.service.UserService;
import com.hanghe.interfaces.queueToken.dto.response.QueueTokenIssueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class QueueTokenFacade {

    private final UserService userService;
    private final QueueTokenService queueTokenService;

    /** 토큰 발급 */
    public QueueTokenIssueResponse generateToken(Long userId) {
        User user = userService.findUser(userId);
        userService.expireUserTokens(user);
        QueueToken queueToken = queueTokenService.generateToken(user);
        return QueueTokenIssueResponse.from(userId, queueToken.getToken(),queueToken.getExpiredAt());
    }

}
