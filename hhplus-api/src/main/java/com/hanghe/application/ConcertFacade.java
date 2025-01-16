package com.hanghe.application;

import com.hanghe.domain.concert.service.ConcertService;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import com.hanghe.interfaces.concert.dto.response.ConcertDateResponse;
import com.hanghe.interfaces.concert.dto.response.ConcertSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConcertFacade {

    private final ConcertService concertService;

    /** 예약 가능 날짜 조회 */
    public List<ConcertDateResponse> findAvailableConcertScheduleDate(Long concertId) {
        List<ConcertScheduleDTO> schedules = concertService.findAllConcertScheduleDate(concertId);
        return schedules.stream()
                .map(ConcertDateResponse::from)
                .toList();
    }

    /** 예약 가능 좌석 조회*/
    public List<ConcertSeatResponse> findAvailableConcertSeat(Long concertScheduleId){
        List<ConcertSeatDTO> seats = concertService.findAllConcsertScheduleSeat(concertScheduleId);
        return seats.stream()
                .map(ConcertSeatResponse::from)
                .toList();
    }


}
