package com.hanghe.domain.concert.entity;

import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.base.enums.SeatType;
import com.hanghe.domain.seat.entity.Seat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Concert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "concert")
    private List<ConcertSchedule> concertScheduleList;

}
