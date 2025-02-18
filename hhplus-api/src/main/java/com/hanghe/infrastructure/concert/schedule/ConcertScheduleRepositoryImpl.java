package com.hanghe.infrastructure.concert.schedule;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.ConcertSchedule;
import com.hanghe.domain.concert.repository.ConcertScheduleRepository;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertScheduleRepositoryImpl implements ConcertScheduleRepository {

  private final ConcertScheduleJpaRepository concertScheduleJpaRepository;
  private final ConcertScheduleQueryDslRepository concertScheduleQueryDslRepository;

    @Override
    public ConcertSchedule save(ConcertSchedule concertSchedule) {
        return concertScheduleJpaRepository.save(concertSchedule);
    }

    @Override
    public List<ConcertSchedule> findAllByPerformanceDateBetween(LocalDate startDate, LocalDate endDate) {
        return concertScheduleJpaRepository.findAllByPerformanceDateBetween(startDate,endDate);
    }

    @Override
    public List<ConcertSchedule> findAllByPerformanceDate(LocalDate date) {
        return concertScheduleJpaRepository.findAllByPerformanceDate(date);
    }

    @Override
    public List<ConcertScheduleDTO> findAvailableDatesByConcertIdWithinRange(Long concertId, LocalDate startDate, LocalDate endDate) {
        return concertScheduleQueryDslRepository.findAvailableDatesByConcertIdWithinRange(concertId,startDate,endDate);
    }
}
