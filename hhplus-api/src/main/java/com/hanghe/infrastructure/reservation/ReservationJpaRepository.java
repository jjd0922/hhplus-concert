package com.hanghe.infrastructure.reservation;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByStatusAndExpiredAtBefore(ReservationStatus status, LocalDateTime dateTime);

    Reservation findByConcertSeatAndStatusNotIn(ConcertSeat concertSeat, List<ReservationStatus> status);

    List<Reservation> findAllByConcertSeat(ConcertSeat concertSeat);
}
