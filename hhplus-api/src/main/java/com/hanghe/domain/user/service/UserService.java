package com.hanghe.domain.user.service;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.payment.entity.PaymentType;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import com.hanghe.domain.user.service.dto.UserBalanceDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /** 사용자 조회 */
    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        return userRepository.findById(userId);
    }

    /** 사용자 잔액 조회 */
    @Transactional(readOnly = true)
    public UserBalanceDTO findUserBalance(Long userId) {
        User user = userRepository.findById(userId);
        return new UserBalanceDTO(userId,user.getBalance());
    }

    /** 잔액 충전 */
    @Transactional
    public void chargeBalance(User user, Long balance){
        try {
            user.chargeBalance(balance);
            userRepository.save(user);
        }catch (OptimisticLockException e){
            throw new BusinessException(ErrorCode.USER_OPTIMISTIC_LOCK_EXCEPTION);
        }
    }
    /** 잔액 사용 */
    @Transactional
    public void useBalance(User user, Long balance){
        try {
            user.useBalance(balance);
            userRepository.save(user);
        }catch (OptimisticLockException e){
            throw new BusinessException(ErrorCode.USER_OPTIMISTIC_LOCK_EXCEPTION);
        }
    }
}
