package com.hanghe.domain.payment.entity;

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
        this.user = user;
        this.amount = amount;
        this.type = type;
    }

    public static Payment create(User user, int amount, PaymentType type) {
        if (type == PaymentType.CHARGE) {
            user.chargeBalance(amount);
        } else if (type == PaymentType.USE) {
            user.useBalance(amount);
        }
        return new Payment(user, amount, type);
    }

}
