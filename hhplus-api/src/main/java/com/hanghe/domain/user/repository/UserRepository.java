package com.hanghe.domain.user.repository;

import com.hanghe.domain.user.entity.User;

public interface UserRepository {

    User save(User user);
    User findById(Long userId);
}
