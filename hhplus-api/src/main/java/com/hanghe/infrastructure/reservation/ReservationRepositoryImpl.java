package com.hanghe.infrastructure.reservation;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.entity.ReservationStatus;
import com.hanghe.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationJpaRepository.save(reservation);
    }

    @Override
    public List<Reservation> saveAll(List<Reservation> reservations) {
        return reservationJpaRepository.saveAll(reservations);
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationJpaRepository.findById(reservationId).orElseThrow(()-> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));
    }

    @Override
    public List<Reservation> findAllByStatusAndExpiredAtBefore(ReservationStatus status, LocalDateTime dateTime) {
        return reservationJpaRepository.findAllByStatusAndExpiredAtBefore(status,dateTime);
    }

    @Override
    public Reservation findByConcertSeatAndStatusNotIn(ConcertSeat concertSeat, List<ReservationStatus> status) {
        return reservationJpaRepository.findByConcertSeatAndStatusNotIn(concertSeat, status);
    }
}
