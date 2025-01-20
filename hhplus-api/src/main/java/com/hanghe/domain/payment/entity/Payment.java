package com.hanghe.domain.payment.entity;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    private int amount;

    private Payment(User user, int amount, PaymentType type) {
        validateInputs(user, amount, type);
        this.user = user;
        this.amount = amount;
        this.type = type;
    }

    public static Payment create(User user, int amount, PaymentType type) {
        Payment payment = new Payment(user, amount, type);
        return payment;
    }

    private void validateInputs(User user, int amount, PaymentType type) {
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        if (type == null) {
            throw new BusinessException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        if (amount <= 0) {
            throw new BusinessException(ErrorCode.PAYMENT_MINUS_AMOUNT);
        }
    }
}
