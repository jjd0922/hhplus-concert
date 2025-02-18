package com.hanghe.application;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.service.ConcertSeatService;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.service.ReservationService;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.service.UserService;
import com.hanghe.interfaces.reservation.dto.request.ReservationRequest;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class ReservationFacade {

    private final UserService userService;
    private final ConcertSeatService concertSeatService;
    private final ReservationService reservationService;

    /** 콘서트 좌석 예약 */
    @Transactional
    public ReservationResponce seatReserve(ReservationRequest requestBody){
        try{
            User user = userService.findUser(requestBody.getUserId());
            ConcertSeat concertSeat = concertSeatService.findSeat(requestBody.getSeatId());
            // 예약처리
            ReservationResponce reservationResponce = reservationService.seatReserve(user, concertSeat);
            // 좌석 상태 업데이트
            concertSeatService.updateStateWaiting(requestBody.getSeatId());
            return reservationResponce;
        }catch(OptimisticLockingFailureException e){
            throw new BusinessException(ErrorCode.RESERVATION_SEAT_FAIL);
        }
    }
}
