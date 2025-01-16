package com.hanghe.domain.concert.repository.custom;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface ConcertScheduleRepositoryCustom {
    List<ConcertScheduleDTO> findAvailableDatesByConcertIdWithinRange(Long concertId, LocalDate startDate, LocalDate endDate);
}
