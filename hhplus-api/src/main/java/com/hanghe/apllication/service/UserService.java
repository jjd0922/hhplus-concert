package com.hanghe.apllication.service;

import com.hanghe.domain.queueToken.repository.QueueTokenRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    /** 사용자 조회 */
    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("해당 유저가 없습니다."));
    }


}
