package com.hanghe.domain.reservation.service;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
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
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        if (concertSeat == null) {
            throw new BusinessException(ErrorCode.CONCERT_SEAT_NOT_FOUND);
        }
        Reservation reservation = Reservation.reserve(user, concertSeat);
        reservationRepository.save(reservation);
        return ReservationResponce.from(reservation);
    }
}
