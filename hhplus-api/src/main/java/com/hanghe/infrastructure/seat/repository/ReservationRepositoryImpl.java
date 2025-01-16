package com.hanghe.infrastructure.seat.repository;

import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.repository.ReservationRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Reservation> findReservePossibleSeat() {
        return null;
    }
}
