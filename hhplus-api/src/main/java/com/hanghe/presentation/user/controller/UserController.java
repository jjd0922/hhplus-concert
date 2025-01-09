package com.hanghe.presentation.user.controller;

import com.hanghe.apllication.service.UserService;
import com.hanghe.presentation.user.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /** 잔액 조회*/
    @GetMapping("/balance/{userId}")
    public ResponseEntity<UserBalanceResponse> selectUserBalance(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(UserBalanceResponse.from(userId,userService.findUser(userId).getBalance()));
    }

}
