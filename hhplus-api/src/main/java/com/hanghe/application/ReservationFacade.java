package com.hanghe.application;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.service.ConcertSeatService;
import com.hanghe.domain.reservation.service.ReservationService;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.service.UserService;
import com.hanghe.interfaces.reservation.dto.request.ReservationRequest;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationFacade {

    private final UserService userService;
    private final ConcertSeatService concertSeatService;
    private final ReservationService reservationService;

    /** 콘서트 좌석 예약 */
    public ReservationResponce seatReserve(ReservationRequest requestBody){
        User user = userService.findUser(requestBody.getUserId());
        ConcertSeat concertSeat = concertSeatService.findSeat(requestBody.getSeatId());
        return reservationService.seatReserve(user, concertSeat);
    }
}
