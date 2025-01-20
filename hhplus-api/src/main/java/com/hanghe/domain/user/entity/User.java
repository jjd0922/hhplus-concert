package com.hanghe.domain.user.entity;


import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.payment.entity.PaymentType;
import com.hanghe.domain.queue.entity.QueueStatus;
import com.hanghe.domain.payment.entity.Payment;
import com.hanghe.domain.queue.entity.QueueToken;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int balance;

    public static User create(Long id, String name) {
        return new User(id,name,0);
    }

    // 잔액 충전
    public void chargeBalance(int amount) {
        if (amount <= 0) {
            throw new BusinessException(ErrorCode.USER_MINUS_AMOUNT);
        }
        this.balance += amount;
    }

    // 잔액 사용
    public void useBalance(int amount) {
        if (amount <= 0) {
            throw new BusinessException(ErrorCode.USER_MINUS_AMOUNT);
        }
        if (this.balance < amount) {
            throw new BusinessException(ErrorCode.USER_MINUS_AMOUNT);
        }
        this.balance -= amount;
    }


}
