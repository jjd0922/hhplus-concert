package com.hanghe.interfaces.concert.controller;

import com.hanghe.application.ConcertFacade;
import com.hanghe.interfaces.concert.dto.response.ConcertDateResponse;
import com.hanghe.interfaces.concert.dto.response.ConcertSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concert")
public class ConcertController {

    private final ConcertFacade concertFacade;

    /** 예약 가능 날짜 확인 */
    @GetMapping("/{concertId}/available-date")
    public ResponseEntity<List<ConcertDateResponse>> possibleDateCheck(@PathVariable Long concertId) {
        return ResponseEntity.ok(concertFacade.findAvailableConcertScheduleDate(concertId));
    }
    /** 예약 가능 좌석 확인 */
    @GetMapping("/{concertScheduleId}/available-seat")
    public ResponseEntity<List<ConcertSeatResponse>> possibleSeatCheck(@PathVariable Long concertScheduleId) {
        return ResponseEntity.ok(concertFacade.findAvailableConcertSeat(concertScheduleId));
    }

}
