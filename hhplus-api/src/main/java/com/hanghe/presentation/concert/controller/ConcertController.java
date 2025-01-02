package com.hanghe.presentation.concert.controller;

import com.hanghe.presentation.concert.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concert")
public class ConcertController {

    /** 예약 가능 날짜 확인 */
    @PostMapping("/possible-date-check")
    public ResponseEntity<ConcertDateResponse> possibleDateCheck(@RequestBody ConcertDateRequest requestBody) {
        List<LocalDate> list = List.of(LocalDate.now(),LocalDate.now().minusDays(1));
        return ResponseEntity.ok(ConcertDateResponse.builder().possibleDate(list).build());
    }
    /** 예약 가능 좌석 확인 */
    @PostMapping("/possible-seat-check")
    public ResponseEntity<ConcertSeatResponse> possibleSeatCheck(@RequestBody ConcertSeatRequest requestBody) {
        List<Long> list = List.of(1L,11L,21L);
        return ResponseEntity.ok(ConcertSeatResponse.builder().seatList(list).userId(1L).token("111-22-33").build());
    }
    /** 좌석 예약 */
    @PostMapping("/seat/reservation")
    public ResponseEntity<ConcertSeatReservationResponce> seatReservation(@RequestBody ConcertSeatReservationRequest requestBody) {
        return ResponseEntity.ok(ConcertSeatReservationResponce.builder().seatId(10L).userId(1L).token("111-22-33").build());
    }

}
