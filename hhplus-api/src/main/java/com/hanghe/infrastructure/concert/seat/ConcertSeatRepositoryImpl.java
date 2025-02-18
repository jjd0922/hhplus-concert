package com.hanghe.infrastructure.concert.seat;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.*;
import com.hanghe.domain.concert.repository.ConcertSeatRepository;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertSeatRepositoryImpl implements ConcertSeatRepository {

    private final ConcertSeatJpaRepository concertSeatJpaRepository;
    private final ConcertSeatQueryDslRepository concertSeatQueryDslRepository;

    @Override
    public ConcertSeat save(ConcertSeat concertSeat) {
        return concertSeatJpaRepository.save(concertSeat);
    }

    @Override
    public ConcertSeat findById(Long seatId) {
        return concertSeatJpaRepository.findById(seatId).orElseThrow(()-> new BusinessException(ErrorCode.CONCERT_SEAT_NOT_FOUND));
    }

    @Override
    public List<ConcertSeat> findAllByConcertScheduleAndStatus(ConcertSchedule concertSchedule, SeatStatus type) {
        return concertSeatJpaRepository.findAllByConcertScheduleAndStatus(concertSchedule,type);
    }

    @Override
    public List<ConcertSeatDTO> findAvailableSeatsByConcertScheduleId(Long concertScheduleId) {
        return concertSeatQueryDslRepository.findAvailableSeatsByConcertScheduleId(concertScheduleId);
    }
}
