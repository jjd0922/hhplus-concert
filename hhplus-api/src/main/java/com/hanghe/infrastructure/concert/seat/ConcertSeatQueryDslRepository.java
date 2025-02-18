package com.hanghe.infrastructure.concert.seat;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.entity.QConcertSchedule;
import com.hanghe.domain.concert.entity.QConcertSeat;
import com.hanghe.domain.concert.entity.SeatStatus;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConcertSeatQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager entityManager;

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

    public Optional<ConcertSeat> findConcertSeatWithPessimisticLock(Long seatId){
        return Optional.ofNullable(entityManager.find(ConcertSeat.class, seatId, LockModeType.PESSIMISTIC_WRITE));
    }

}
