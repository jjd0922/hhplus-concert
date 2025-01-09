package com.hanghe.presentation.concert.controller;

import com.hanghe.apllication.service.ConcertService;
import com.hanghe.presentation.concert.dto.request.ConcertDateRequest;
import com.hanghe.presentation.concert.dto.request.ConcertSeatRequest;
import com.hanghe.presentation.concert.dto.request.ConcertSeatReservationRequest;
import com.hanghe.presentation.concert.dto.response.ConcertDateResponse;
import com.hanghe.presentation.concert.dto.response.ConcertSeatReservationResponce;
import com.hanghe.presentation.concert.dto.response.ConcertSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concert")
public class ConcertController {

    private final ConcertService concertService;

    /** 예약 가능 날짜 확인 */
    @PostMapping("/possible-date-check")
    public ResponseEntity<ConcertDateResponse> possibleDateCheck() {
        return ResponseEntity.ok(concertService.selectConcertPossibleDate());
    }
    /** 해당 날짜 예약 가능 좌석 확인 */
    @PostMapping("/possible-seat-check")
    public ResponseEntity<ConcertSeatResponse> possibleSeatCheck(@RequestBody ConcertSeatRequest requestBody) {
        return ResponseEntity.ok(concertService.selectConcertPossibleSeat(requestBody));
    }
    /** 좌석 예약 */
    @PostMapping("/seat/reservation")
    public ResponseEntity<ConcertSeatReservationResponce> seatReservation(@RequestBody ConcertSeatReservationRequest requestBody) {
        return ResponseEntity.ok(concertService.seatReserviation(requestBody));
    }

}
