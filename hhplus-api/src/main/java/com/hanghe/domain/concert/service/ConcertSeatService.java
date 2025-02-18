package com.hanghe.domain.concert.service;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.repository.ConcertSeatRepository;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConcertSeatService {

    private final ConcertSeatRepository concertSeatRepository;

    /** 좌석 조회 */
    @Transactional
    public ConcertSeat findSeat(Long seatId) {
        return concertSeatRepository.findById(seatId);
    }

    @Transactional
    public void updateStateWaiting(Long seatId) {
       ConcertSeat concertSeat = concertSeatRepository.findById(seatId);
        try{
            concertSeat.waiting();
            concertSeatRepository.save(concertSeat);
        }catch(OptimisticLockingFailureException e){
            throw new BusinessException(ErrorCode.RESERVATION_SEAT_FAIL);
        }
    }
}
