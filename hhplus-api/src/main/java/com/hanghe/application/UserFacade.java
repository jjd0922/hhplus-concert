package com.hanghe.application;

import com.hanghe.domain.user.service.UserService;
import com.hanghe.interfaces.user.dto.response.UserBalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFacade {

    private final UserService userService;

    /** 유저 잔액 조회 */
    public UserBalanceResponse findUserBalance(Long userId){
        return UserBalanceResponse.from(userService.findUserBalance(userId));
    }

}
