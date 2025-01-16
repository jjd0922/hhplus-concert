package com.hanghe.domain.concert.service;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.repository.ConcertSeatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConcertSeatService {

    private final ConcertSeatRepository concertSeatRepository;

    /** 좌석 조회 */
    public ConcertSeat findSeat(Long seatId) {
        return concertSeatRepository.findById(seatId).orElseThrow(()-> new EntityNotFoundException("해당 좌석이 없습니다."));
    }
}
