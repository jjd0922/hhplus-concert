package com.hanghe.domain.reservation.service;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.repository.ReservationRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /** 콘서트 좌석 예약 */
    public ReservationResponce seatReserve(User user, ConcertSeat concertSeat){
        Reservation reservation = Reservation.reserve(user, concertSeat);
        reservationRepository.save(reservation);
        return ReservationResponce.from(reservation);
    }
}
