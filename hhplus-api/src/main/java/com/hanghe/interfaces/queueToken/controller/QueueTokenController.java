package com.hanghe.interfaces.queueToken.controller;

import com.hanghe.application.QueueTokenFacade;
import com.hanghe.common.annotation.NotTokenCheck;
import com.hanghe.interfaces.queueToken.dto.response.QueueTokenIssueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/queue")
public class QueueTokenController {

    private final QueueTokenFacade queueTokenFacade;

    /** 토큰 발행*/
    @NotTokenCheck
    @GetMapping("/token-issuance/{userId}")
    public ResponseEntity<QueueTokenIssueResponse> createUserToken(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(queueTokenFacade.generateToken(userId));
    }

}
