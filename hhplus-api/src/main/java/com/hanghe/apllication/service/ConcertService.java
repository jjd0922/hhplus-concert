package com.hanghe.apllication.service;

import com.hanghe.domain.base.enums.SeatType;
import com.hanghe.domain.concert.entity.ConcertSchedule;
import com.hanghe.domain.concert.repository.ConcertRepository;
import com.hanghe.domain.concert.repository.ConcertScheduleRepository;
import com.hanghe.domain.seat.entity.Seat;
import com.hanghe.domain.seat.entity.SeatReservation;
import com.hanghe.domain.seat.repository.SeatReservationRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.presentation.concert.dto.request.ConcertDateRequest;
import com.hanghe.presentation.concert.dto.request.ConcertSeatRequest;
import com.hanghe.presentation.concert.dto.request.ConcertSeatReservationRequest;
import com.hanghe.presentation.concert.dto.response.ConcertDateResponse;
import com.hanghe.presentation.concert.dto.response.ConcertSeatReservationResponce;
import com.hanghe.presentation.concert.dto.response.ConcertSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ConcertService {

    private final UserService userService;
    private final SeatService seatService;
    private final SeatReservationRepository seatReservationRepository;
    private final ConcertScheduleRepository concertScheduleRepository;

    /** 콘서트 예약 가능 날짜 조회*/
    public ConcertDateResponse selectConcertPossibleDate(){
        return ConcertDateResponse.from(concertScheduleRepository.findAllByPerformanceDateBetween(LocalDate.now(), LocalDate.now().plusMonths(1))
                .stream()
                .filter(concertSchedule -> !concertSchedule.isSeatFull()) // 좌석이 가득 차지 않은 스케줄 필터링
                .map(ConcertSchedule::getPerformanceDate)                 // 날짜 추출
                .collect(Collectors.toSet()));
    }

    /** 콘서트 예약 가능 좌석 조회*/
    public ConcertSeatResponse selectConcertPossibleSeat(ConcertSeatRequest requestBody){
        return ConcertSeatResponse.from(requestBody.getDate(),
                concertScheduleRepository.findAllByPerformanceDate(requestBody.getDate()).stream()
                        .collect(Collectors.toMap(
                                ConcertSchedule::getId,
                                schedule -> schedule.getWaitSeatList().stream()
                                        .map(Seat::getId)
                                        .toList()
                        ))
        );
    }

    /** 콘서트 좌석 예약 */
    public ConcertSeatReservationResponce seatReserviation(ConcertSeatReservationRequest requestBody){
        User user = userService.findUser(requestBody.getUserId());
        Seat seat = seatService.findSeat(requestBody.getSeatId());
        SeatReservation reservation = SeatReservation.reservation(user,seat);
        seatReservationRepository.save(reservation);
        return ConcertSeatReservationResponce.from(reservation);
    }

}
