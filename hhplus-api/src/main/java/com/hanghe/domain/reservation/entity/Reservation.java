package com.hanghe.domain.reservation.entity;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.payment.entity.Payment;
import com.hanghe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID", referencedColumnName = "id")
    private ConcertSeat concertSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime reservedAt;
    private LocalDateTime expiredAt;

    @Version
    private int version;

    private Reservation(User user, ConcertSeat concertSeat,ReservationStatus status) {
        this.user = user;
        this.concertSeat = concertSeat;
        this.status = status;
        this.reservedAt = LocalDateTime.now();
        this.expiredAt = LocalDateTime.now().plusMinutes(5);
    }

    /** 좌석 예약 */
    public static Reservation reserve(User user, ConcertSeat concertSeat) {
        return new Reservation(user, concertSeat,ReservationStatus.WAIT);
    }

    /** 예약 완료 처리*/
    public void complete() {
        if (this.status == ReservationStatus.COMPLETE) {
            throw new BusinessException(ErrorCode.RESERVATION_COMPLETE);
        }
        if (this.status == ReservationStatus.CANCEL) {
            throw new BusinessException(ErrorCode.RESERVATION_CANCEL);
        }
        this.status = ReservationStatus.COMPLETE;
    }

    /** 예약 취소 처리*/
    public void cancel() {
        if (this.status == ReservationStatus.CANCEL) {
            throw new BusinessException(ErrorCode.RESERVATION_CANCEL);
        }
        this.status = ReservationStatus.CANCEL;
    }

}
