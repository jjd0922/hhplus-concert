package com.hanghe.infrastructure.user;

import com.hanghe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User,Long> {

}
