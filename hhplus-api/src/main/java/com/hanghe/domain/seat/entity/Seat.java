package com.hanghe.domain.seat.entity;

import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.base.enums.SeatType;
import com.hanghe.domain.concert.entity.ConcertSchedule;
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
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "id")
    private ConcertSchedule concertSchedule;

    @Enumerated(EnumType.STRING)
    private SeatType status;

    private int no;
    private int price;


}
