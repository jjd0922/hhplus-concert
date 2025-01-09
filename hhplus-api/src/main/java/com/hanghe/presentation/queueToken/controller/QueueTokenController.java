package com.hanghe.presentation.queueToken.controller;

import com.hanghe.apllication.service.QueueTokenService;
import com.hanghe.infrastructure.annotation.NotTokenCheck;
import com.hanghe.presentation.queueToken.dto.request.QueueTokenValidRequest;
import com.hanghe.presentation.queueToken.dto.response.QueueTokenIssueResponse;
import com.hanghe.presentation.queueToken.dto.response.QueueTokenValidResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/queue")
public class QueueTokenController {

    private final QueueTokenService queueTokenService;

    /** 토큰 발행*/
    @NotTokenCheck
    @GetMapping("/token-issuance/{userId}")
    public ResponseEntity<QueueTokenIssueResponse> createUserToken(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(queueTokenService.generateToken(userId));
    }

}
