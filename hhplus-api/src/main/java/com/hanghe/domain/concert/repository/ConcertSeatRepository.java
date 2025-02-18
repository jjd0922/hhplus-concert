package com.hanghe.domain.concert.repository;

import com.hanghe.domain.concert.entity.ConcertSchedule;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.entity.SeatStatus;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;

import java.util.List;

public interface ConcertSeatRepository {

    ConcertSeat save(ConcertSeat concertSeat);

    ConcertSeat findById(Long seatId);

    List<ConcertSeat> findAllByConcertScheduleAndStatus(ConcertSchedule concertSchedule, SeatStatus type);

    List<ConcertSeatDTO> findAvailableSeatsByConcertScheduleId(Long concertScheduleId);
}
