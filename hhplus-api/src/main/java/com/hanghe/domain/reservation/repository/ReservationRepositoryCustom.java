package com.hanghe.domain.reservation.repository;

import com.hanghe.domain.reservation.entity.Reservation;

import java.util.List;

public interface ReservationRepositoryCustom {
    List<Reservation> findReservePossibleSeat();
}
