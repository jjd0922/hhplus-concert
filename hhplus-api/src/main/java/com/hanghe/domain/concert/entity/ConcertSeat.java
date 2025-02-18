package com.hanghe.domain.concert.entity;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.reservation.entity.ReservationStatus;
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
public class ConcertSeat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "id")
    private ConcertSchedule concertSchedule;

    private int no;
    private int price;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Version
    private int version;

    private ConcertSeat(Long id, ConcertSchedule concertSchedule, int no, int price, SeatStatus status) {
        this.id = id;
        this.concertSchedule = concertSchedule;
        this.no = no;
        this.price = price;
        this.status = status;
    }

    public static ConcertSeat create(Long id, ConcertSchedule concertSchedule, int no, int price, SeatStatus status) {
        return new ConcertSeat(id,concertSchedule,no, price,status);
    }

    public void waiting() {
        if (this.status != SeatStatus.AVAILABLE) {
            throw new BusinessException(ErrorCode.CONCERT_SEAT_NOT_AVAILABLE);
        }
        this.status = SeatStatus.WAITING;
    }

}
