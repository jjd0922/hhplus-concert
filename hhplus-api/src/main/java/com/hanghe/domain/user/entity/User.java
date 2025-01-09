package com.hanghe.domain.user.entity;


import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.base.enums.PaymentType;
import com.hanghe.domain.base.enums.QueueStatus;
import com.hanghe.domain.payment.emtity.Payment;
import com.hanghe.domain.queueToken.entity.QueueToken;
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

    @OneToMany(mappedBy = "user")
    private List<QueueToken> userQueueListToken;

    @OneToMany(mappedBy = "user")
    private List<Payment> paymentList;

    /** 해당 유저 전체 토큰 만료*/
    public void setUserTokenExpire(){
        if(userQueueListToken.size() > 0){
            for (QueueToken queueToken : userQueueListToken) {
                queueToken.setStatus(QueueStatus.EXPIRED);
            }
        }
    }

    /** 잔액 update*/
    public int updateBalance(){
        int balance = 0;
        for (Payment payment : paymentList) {
            if(payment.getType().equals(PaymentType.CHARGE)){
                balance += payment.getAmount();
            }else if(payment.getType().equals(PaymentType.USE)){
                balance -= payment.getAmount();
            }
        }
        return balance;
    }




}
