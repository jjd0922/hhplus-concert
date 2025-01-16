package com.hanghe.infrastructure.concert.repository;

import com.hanghe.domain.concert.entity.QConcertSchedule;
import com.hanghe.domain.concert.entity.QConcertSeat;
import com.hanghe.domain.concert.entity.SeatStatus;
import com.hanghe.domain.concert.repository.custom.ConcertScheduleRepositoryCustom;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertScheduleRepositoryImpl implements ConcertScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ConcertScheduleDTO> findAvailableDatesByConcertIdWithinRange(Long concertId, LocalDate startDate, LocalDate endDate) {
        QConcertSchedule concertSchedule = QConcertSchedule.concertSchedule;
        QConcertSeat concertSeat = QConcertSeat.concertSeat;

        return queryFactory.select(
                        Projections.constructor(ConcertScheduleDTO.class,concertSchedule.id, concertSchedule.performanceDate))
                .from(concertSchedule)
                .join(concertSeat).on(concertSeat.concertSchedule.eq(concertSchedule))
                .where(concertSchedule.concert.id.eq(concertId)
                        .and(concertSeat.status.eq(SeatStatus.AVAILABLE))
                        .and(concertSchedule.performanceDate.between(startDate, endDate)))
                .distinct()
                .fetch();
    }
}
