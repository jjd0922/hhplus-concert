package com.hanghe.domain.payment.emtity;

import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.base.enums.PaymentStatus;
import com.hanghe.domain.base.enums.PaymentType;
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

    public static Payment createPayment(User user, int amount, PaymentType type) {
        if (type == PaymentType.USE && user.getBalance() < amount) {
            throw new RuntimeException("잔액이 부족합니다.");
        }
        return Payment.builder()
                .user(user)
                .amount(amount)
                .type(type)
                .build();
    }

}
