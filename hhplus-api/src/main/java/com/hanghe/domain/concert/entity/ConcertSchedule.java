package com.hanghe.domain.concert.entity;

import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.base.enums.SeatType;
import com.hanghe.domain.seat.entity.Seat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class ConcertSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONCERT_ID", referencedColumnName = "id")
    private Concert concert;

    private LocalDate performanceDate;
    private int seatLimit;

    @OneToMany(mappedBy = "concertSchedule")
    private List<Seat> seatList;

    /** 만석 체크*/
    public boolean isSeatFull(){
       return seatList != null && seatList.size() >= seatLimit ? true : false;
    }

    /** 예약 가능 좌석 리스트 */
    public List<Seat> getWaitSeatList(){
        List<Seat> seats = new ArrayList<>();
        if(seatList != null){
            for (Seat seat : seatList) {
                if(seat.getStatus().equals(SeatType.WAIT)){
                    seats.add(seat);
                }
            }
        }
        return seats;
    }


}




