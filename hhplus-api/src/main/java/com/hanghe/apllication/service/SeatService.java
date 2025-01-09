package com.hanghe.apllication.service;

import com.hanghe.domain.seat.entity.Seat;
import com.hanghe.domain.seat.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatService {

    private final SeatRepository seatRepository;

    /** 좌석 조회 */
    public Seat findSeat(Long seatId) {
        return seatRepository.findById(seatId).orElseThrow(()-> new EntityNotFoundException("해당 좌석이 없습니다."));
    }
}
