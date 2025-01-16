package com.hanghe.interfaces.reservation.controller;

import com.hanghe.application.ReservationFacade;
import com.hanghe.interfaces.reservation.dto.request.ReservationRequest;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationFacade reservationFacade;

    /** 좌석 예약 */
    @PostMapping("")
    public ResponseEntity<ReservationResponce> seatReservation(@RequestBody ReservationRequest requestBody) {
        return ResponseEntity.ok(reservationFacade.seatReserve(requestBody));
    }
}
