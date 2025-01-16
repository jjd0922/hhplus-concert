package com.hanghe.domain.concert.repository;

import com.hanghe.domain.concert.entity.ConcertSchedule;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertSeatRepository extends JpaRepository<ConcertSeat,Long> {

    List<ConcertSeat> findAllByConcertScheduleAndStatus(ConcertSchedule concertSchedule, SeatStatus type);

}
