package com.hanghe.service;

import com.hanghe.apllication.service.ConcertService;
import com.hanghe.apllication.service.SeatService;
import com.hanghe.apllication.service.UserService;
import com.hanghe.domain.base.enums.SeatType;
import com.hanghe.domain.concert.entity.ConcertSchedule;
import com.hanghe.domain.concert.repository.ConcertScheduleRepository;
import com.hanghe.domain.seat.entity.Seat;
import com.hanghe.domain.seat.entity.SeatReservation;
import com.hanghe.domain.seat.repository.SeatReservationRepository;
import com.hanghe.domain.user.entity.User;
import com.hanghe.presentation.concert.dto.request.ConcertSeatRequest;
import com.hanghe.presentation.concert.dto.request.ConcertSeatReservationRequest;
import com.hanghe.presentation.concert.dto.response.ConcertDateResponse;
import com.hanghe.presentation.concert.dto.response.ConcertSeatReservationResponce;
import com.hanghe.presentation.concert.dto.response.ConcertSeatResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private SeatService seatService;
    @Mock
    private SeatReservationRepository seatReservationRepository;
    @Mock
    private ConcertScheduleRepository concertScheduleRepository;
    @InjectMocks
    private ConcertService concertService;

    @Test
    @DisplayName("콘서트 예약 가능 날짜 조회 테스트")
    void selectConcertPossibleDateTest() {

        // given
        List<ConcertSchedule> schedules = List.of(
                ConcertSchedule.builder()
                        .id(1L)
                        .performanceDate(LocalDate.now().plusDays(1))
                        .seatLimit(30)
                        .build(),
                ConcertSchedule.builder()
                        .id(2L)
                        .performanceDate(LocalDate.now().plusDays(2))
                        .seatLimit(20)
                        .build()
        );

        when(concertScheduleRepository.findAllByPerformanceDateBetween(LocalDate.now(), LocalDate.now().plusMonths(1)))
                .thenReturn(schedules);

        // when
        ConcertDateResponse result = concertService.selectConcertPossibleDate();

        // then
        Assertions.assertEquals(2,result.getPossibleDate().size());

    }

    @Test
    @DisplayName("콘서트 예약 가능 좌석 조회 테스트 - 상태값 WAIT 인 좌석만 리턴")
    void selectConcertPossibleSeatTest() {
        // given
        LocalDate testDate = LocalDate.of(2025, 1, 15);

        ConcertSchedule schedule1 = ConcertSchedule.builder()
                .id(1L)
                .performanceDate(testDate)
                .build();

        ConcertSchedule schedule2 = ConcertSchedule.builder()
                .id(2L)
                .performanceDate(testDate)
                .build();

        Seat seat1 = new Seat(1L, schedule1, SeatType.WAIT,1,1000);
        Seat seat2 = new Seat(2L, schedule1, SeatType.WAIT,1,1000);
        Seat seat3 = new Seat(3L, schedule1, SeatType.COMPLETE,1,1000);
        schedule1.setSeatList(List.of(seat1, seat2, seat3));

        Seat seat4 = new Seat(4L, schedule2, SeatType.COMPLETE,1,1000);
        Seat seat5 = new Seat(5L, schedule2, SeatType.WAIT,1,1000);
        Seat seat6 = new Seat(6L, schedule2, SeatType.COMPLETE,1,1000);
        schedule2.setSeatList(List.of(seat4, seat5, seat6));

        when(concertScheduleRepository.findAllByPerformanceDate(testDate))
                .thenReturn(List.of(schedule1, schedule2));

        ConcertSeatRequest request = ConcertSeatRequest.builder()
                .date(testDate)
                .build();

        // when
        ConcertSeatResponse result = concertService.selectConcertPossibleSeat(request);

        // then
        Map<Long, List<Long>> expectedSeatMap = Map.of(
                1L, List.of(1L, 2L),
                2L, List.of(5L)
        );

        Assertions.assertEquals(testDate, result.getDate());
        Assertions.assertEquals(expectedSeatMap, result.getSeatList());
    }

    @Test
    @DisplayName("콘서트 좌석 예약 테스트")
    void seatReserviationTest() {
        // given
        Long userId = 1L;
        Long seatId = 10L;

        User user = User.builder().id(userId).name("테스트 유저").build();
        Seat seat = Seat.builder().id(seatId).status(SeatType.WAIT).build();

        SeatReservation reservation = SeatReservation.builder()
                .id(100L)
                .seat(seat)
                .user(user)
                .reservedAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(5))
                .build();

        ConcertSeatReservationRequest request = ConcertSeatReservationRequest.builder()
                .userId(userId)
                .seatId(seatId)
                .build();

        when(userService.findUser(userId)).thenReturn(user);
        when(seatService.findSeat(seatId)).thenReturn(seat);
        when(seatReservationRepository.save(any(SeatReservation.class))).thenReturn(reservation);

        // when
        ConcertSeatReservationResponce result = concertService.seatReserviation(request);

        // then
        Assertions.assertEquals(userId, result.getUserId());
        Assertions.assertEquals(seatId, result.getSeatId());

    }

    @Test
    @DisplayName("콘서트 좌석 예약 테스트 - 이미 예약완료 좌석일 시 예외처리")
    void seatReserviationTest_fail() {
        // given
        Long userId = 1L;
        Long seatId = 10L;

        User user = User.builder().id(userId).name("테스트 유저").build();
        Seat seat = Seat.builder().id(seatId).status(SeatType.COMPLETE).build();

        ConcertSeatReservationRequest request = ConcertSeatReservationRequest.builder()
                .userId(userId)
                .seatId(seatId)
                .build();

        when(userService.findUser(userId)).thenReturn(user);
        when(seatService.findSeat(seatId)).thenReturn(seat);

        // when & then
        Assertions.assertThrows(RuntimeException.class,
                () -> concertService.seatReserviation(request),
                "이미 예약완료된 좌석입니다."
        );
    }
}
