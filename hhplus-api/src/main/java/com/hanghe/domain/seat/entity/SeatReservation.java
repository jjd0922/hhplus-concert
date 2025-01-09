package com.hanghe.domain.seat.entity;

import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.base.enums.PaymentType;
import com.hanghe.domain.base.enums.SeatType;
import com.hanghe.domain.payment.emtity.Payment;
import com.hanghe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SeatReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID", referencedColumnName = "id")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "id")
    private Payment payment;

    private LocalDateTime reservedAt;
    private LocalDateTime expiredAt;

    /** 좌석 예약 */
    public static SeatReservation reservation(User user, Seat seat) {
        if(seat.getStatus().equals(SeatType.COMPLETE)){
            throw new RuntimeException("이미 예약완료된 좌석입니다.");
        }
        return SeatReservation.builder()
                .user(user)
                .seat(seat)
                .reservedAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(5))
                .build();
    }
}
