package com.hanghe.interfaces.user.controller;

import com.hanghe.application.UserFacade;
import com.hanghe.interfaces.user.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
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
        UserBalanceResponse response = userFacade.findUserBalance(userId);
        if (response == null) {
            System.out.println("UserBalanceResponse is NULL!");
        } else {
            System.out.println("UserBalanceResponse: " + response);
        }

        return ResponseEntity.ok(response);
    }


}
