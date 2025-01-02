package com.hanghe.presentation.users.controller;

import com.hanghe.presentation.users.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    /** 토큰 검증*/
    @GetMapping("/token-check/{userId}")
    public ResponseEntity<UsersResponse> checkUserToken(@PathVariable("userId") Long userId) {
        UUID myUuid = UUID.randomUUID();
        return ResponseEntity.ok(UsersResponse.builder().userId(1L).token(myUuid).expiredAt(LocalDateTime.now()).status("EXPIRED").build());
    }

    /** 토큰 발행*/
    @GetMapping("/token-issuance/{userId}")
    public ResponseEntity<UsersResponse> createUserToken(@PathVariable("userId") Long userId) {
        UUID myUuid = UUID.randomUUID();
        return ResponseEntity.ok(UsersResponse.builder().userId(1L).token(myUuid).expiredAt(LocalDateTime.now()).status("WATT").build());
    }

    /** 대기열 체크*/
    @PostMapping("/token-queue-check")
    public ResponseEntity<UsersTokenResponse> queueCheck(@RequestBody UsersTokenRequest requestBody) {
        UUID myUuid = UUID.randomUUID();
        return ResponseEntity.ok(UsersTokenResponse.builder().token(myUuid).status("WAIT").userId(1L).leftCount(10).expiredAt(LocalDateTime.now()).build());
    }

    /** 잔액 조회*/
    @GetMapping("/balance/{userId}")
    public ResponseEntity<UsersBalanceResponse> selectUserBalance(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(UsersBalanceResponse.builder().userId(1L).balance(2000).build());
    }

    /** 잔액 충전*/
    @PostMapping("/charge")
    public ResponseEntity<UsersChargeResponse> userCharge(@RequestBody UsersChargeRequest requestBody) {
        return ResponseEntity.ok(UsersChargeResponse.builder().userId(1L).balance(30000).build());
    }
}
