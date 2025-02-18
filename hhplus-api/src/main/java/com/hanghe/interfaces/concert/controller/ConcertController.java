package com.hanghe.interfaces.concert.controller;

import com.hanghe.application.ConcertFacade;
import com.hanghe.interfaces.concert.dto.request.ConcertDateRequest;
import com.hanghe.interfaces.concert.dto.request.ConcertSeatRequest;
import com.hanghe.interfaces.concert.dto.response.ConcertDateResponse;
import com.hanghe.interfaces.concert.dto.response.ConcertSeatResponse;
import com.hanghe.interfaces.payment.dto.request.PaymentChargeRequest;
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
    @PostMapping("/available-date")
    public ResponseEntity<List<ConcertDateResponse>> possibleDateCheck(@RequestBody ConcertDateRequest requestBody) {
        return ResponseEntity.ok(concertFacade.findAvailableConcertScheduleDate(requestBody.concertId()));
    }
    /** 예약 가능 좌석 확인 */
    @PostMapping("/available-seat")
    public ResponseEntity<List<ConcertSeatResponse>> possibleSeatCheck(@RequestBody ConcertSeatRequest requestBody) {
        return ResponseEntity.ok(concertFacade.findAvailableConcertSeat(requestBody.concertScheduleId()));
    }

}
