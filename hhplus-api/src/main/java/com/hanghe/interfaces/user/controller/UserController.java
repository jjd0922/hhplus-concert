package com.hanghe.interfaces.user.controller;

import com.hanghe.application.UserFacade;
import com.hanghe.interfaces.user.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    /** 잔액 조회*/
    @GetMapping("/balance/{userId}")
    public ResponseEntity<UserBalanceResponse> selectUserBalance(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userFacade.findUserBalance(userId));
    }

}
