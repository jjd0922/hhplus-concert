package com.hanghe.domain.concert.service;

import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import com.hanghe.infrastructure.concert.repository.ConcertScheduleRepositoryImpl;
import com.hanghe.infrastructure.concert.repository.ConcertSeatRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConcertService {
    private final ConcertScheduleRepositoryImpl concertScheduleRepositoryImpl;
    private final ConcertSeatRepositoryImpl concertSeatRepositoryImpl;

    /** 콘서트 예약 가능 날짜 조회*/
    public List<ConcertScheduleDTO> findAllConcertScheduleDate(Long concertId){
        if (concertId == null) {
            throw new IllegalArgumentException("Invalid concertId");
        }
        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusMonths(1);
        return concertScheduleRepositoryImpl.findAvailableDatesByConcertIdWithinRange(concertId, today, nextMonth);
    }

    /** 콘서트 예약 가능 좌석 조회*/
    public List<ConcertSeatDTO> findAllConcsertScheduleSeat(Long concertScheduleId){
        if (concertScheduleId == null) {
            throw new IllegalArgumentException("Invalid concertScheduleId");
        }
        return concertSeatRepositoryImpl.findAvailableSeatsByConcertScheduleId(concertScheduleId);
    }

}
