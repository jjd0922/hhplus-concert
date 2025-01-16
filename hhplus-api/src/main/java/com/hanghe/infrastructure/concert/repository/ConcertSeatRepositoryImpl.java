package com.hanghe.infrastructure.concert.repository;

import com.hanghe.domain.concert.entity.QConcertSchedule;
import com.hanghe.domain.concert.entity.QConcertSeat;
import com.hanghe.domain.concert.entity.SeatStatus;
import com.hanghe.domain.concert.repository.custom.ConcertSeatRepositoryCustom;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertSeatRepositoryImpl implements ConcertSeatRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ConcertSeatDTO> findAvailableSeatsByConcertScheduleId(Long concertScheduleId) {
        QConcertSeat concertSeat = QConcertSeat.concertSeat;
        QConcertSchedule concertSchedule = QConcertSchedule.concertSchedule;

        return queryFactory.select(Projections.constructor(
                        ConcertSeatDTO.class,
                        concertSeat.id,
                        concertSeat.no,
                        concertSeat.price
                ))
                .from(concertSeat)
                .join(concertSchedule).on(concertSeat.concertSchedule.eq(concertSchedule))
                .where(
                        concertSchedule.id.eq(concertScheduleId),
                        concertSeat.status.eq(SeatStatus.AVAILABLE)
                )
                .fetch();

    }

}
