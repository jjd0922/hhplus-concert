package com.hanghe.domain.reservation.service;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.entity.SeatStatus;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.entity.ReservationStatus;
import com.hanghe.domain.reservation.repository.ReservationRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /** 예약 검증*/
    @Transactional
    public void reservationInvalid(long reservationId){
        reservationRepository.findById(reservationId);
    }

    /** 콘서트 좌석 임시 예약 */
    @Transactional
    public ReservationResponce seatReserve(User user, ConcertSeat concertSeat){
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        if (concertSeat == null) {
            throw new BusinessException(ErrorCode.CONCERT_SEAT_NOT_FOUND);
        }
        if (!concertSeat.getStatus().equals(SeatStatus.AVAILABLE)) {
            throw new BusinessException(ErrorCode.CONCERT_SEAT_NOT_AVAILABLE);
        }
        if(reservationRepository.findByConcertSeatAndStatusNotIn(concertSeat, List.of(ReservationStatus.CANCEL)) != null){
            throw new BusinessException(ErrorCode.RESERVATION_SEAT_NOT_AVAILABLE);
        }
        try{
            Reservation reservation = Reservation.reserve(user, concertSeat);
            reservationRepository.save(reservation);
            return ReservationResponce.from(reservation);
        }catch(OptimisticLockingFailureException e){
            throw new BusinessException(ErrorCode.RESERVATION_SEAT_FAIL);
        }
    }

    /** 예약 만료 처리*/
    @Transactional
    public void expireReservation(){
        List<Reservation> expiredReservations = reservationRepository.findAllByStatusAndExpiredAtBefore(ReservationStatus.WAIT, LocalDateTime.now());
        if (expiredReservations.isEmpty()) {
            return;
        }
        expiredReservations.forEach(reservation -> {
            if (reservation.getStatus().equals(ReservationStatus.WAIT)) {
                reservation.cancel();
            }
        });
        reservationRepository.saveAll(expiredReservations);

    }
}
