package com.hanghe.domain.concert.repository.custom;

import com.hanghe.domain.concert.entity.SeatStatus;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;

import java.util.List;

public interface ConcertSeatRepositoryCustom {

    List<ConcertSeatDTO> findAvailableSeatsByConcertScheduleId(Long concertScheduleId);
}
