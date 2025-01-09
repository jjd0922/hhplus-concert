package com.hanghe.domain.seat.repository;

import com.hanghe.domain.seat.entity.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatReservationRepository extends JpaRepository<SeatReservation,Long> {
}
