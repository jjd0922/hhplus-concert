package com.hanghe.domain.concert.service;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.repository.ConcertScheduleRepository;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import com.hanghe.infrastructure.concert.schedule.ConcertScheduleRepositoryImpl;
import com.hanghe.infrastructure.concert.seat.ConcertSeatRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ConcertScheduleRepository concertScheduleRepository;
    private final ConcertSeatRepositoryImpl concertSeatRepositoryImpl;

    /** 콘서트 예약 가능 날짜 조회*/
    @Transactional(readOnly = true)
    public List<ConcertScheduleDTO> findAllConcertScheduleDate(Long concertId){
        if (concertId == null) {
            throw new BusinessException(ErrorCode.CONCERT_NOT_FOUND);
        }
        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusMonths(1);
        return concertScheduleRepository.findAvailableDatesByConcertIdWithinRange(concertId, today, nextMonth);
    }

    /** 콘서트 예약 가능 좌석 조회*/
    @Transactional(readOnly = true)
    public List<ConcertSeatDTO> findAllConcsertScheduleSeat(Long concertScheduleId){
        if (concertScheduleId == null) {
            throw new BusinessException(ErrorCode.CONCERT_SCEDULE_NOT_FOUND);
        }
        return concertSeatRepositoryImpl.findAvailableSeatsByConcertScheduleId(concertScheduleId);
    }

}
