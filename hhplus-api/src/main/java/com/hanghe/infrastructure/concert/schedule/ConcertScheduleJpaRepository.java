package com.hanghe.infrastructure.concert.schedule;

import com.hanghe.domain.concert.entity.Concert;
import com.hanghe.domain.concert.entity.ConcertSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConcertScheduleJpaRepository extends JpaRepository<ConcertSchedule,Long> {
    /** 콘서트 스케줄 리스트 조회*/
    List<ConcertSchedule> findAllByPerformanceDateBetween(LocalDate startDate, LocalDate endDate);

    /** 해당 날짜 콘서트 리스트 조회*/
    List<ConcertSchedule> findAllByPerformanceDate(LocalDate date);
}

