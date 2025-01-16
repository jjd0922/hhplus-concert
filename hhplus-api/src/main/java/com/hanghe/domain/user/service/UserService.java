package com.hanghe.domain.user.service;

import com.hanghe.domain.payment.entity.PaymentType;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import com.hanghe.domain.user.service.dto.UserBalanceDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /** 사용자 조회 */
    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("해당 유저가 없습니다."));
    }

    /** 사용자 잔액 조회 */
    public UserBalanceDTO findUserBalance(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserBalanceDTO(user.getBalance()))
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 없습니다."));
    }

    /** 유저의 기존 토큰 만료 처리 */
    public void expireUserTokens(User user) {
        user.setUserTokenExpire();
    }
}
