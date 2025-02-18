package com.hanghe.domain.reservation.repository;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    List<Reservation> saveAll(List<Reservation> reservations);
    Reservation findById(Long reservationId);
    List<Reservation> findAllByStatusAndExpiredAtBefore(ReservationStatus status, LocalDateTime dateTime);
    Reservation findByConcertSeatAndStatusNotIn(ConcertSeat concertSeat, List<ReservationStatus> status);

}
