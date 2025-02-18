package com.hanghe.domain.reservation;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.Concert;
import com.hanghe.domain.concert.entity.ConcertSchedule;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.entity.SeatStatus;
import com.hanghe.domain.concert.repository.ConcertSeatRepository;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.repository.ReservationRepository;
import com.hanghe.domain.reservation.service.ReservationService;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ConcertSeatRepository concertSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    @DisplayName("콘서트 좌석 정상 예약")
    void seatReserve_Success() {
        // Given
        User user = User.create(1L, "테스트 유저");
        Concert concert = Concert.create(1L,"테스트");
        ConcertSchedule concertSchedule = ConcertSchedule.create(1L,concert,LocalDate.now(),30);
        ConcertSeat concertSeat = ConcertSeat.create(1L,concertSchedule,1,1000,SeatStatus.AVAILABLE);
        Reservation.reserve(user, concertSeat);

        // When
        ReservationResponce response = reservationService.seatReserve(user, concertSeat);

        // Then
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("콘서트 좌석 예약 - 유저가 null 일 때 예외 발생")
    void seatReserve_ShouldThrowException_WhenUserIsNull() {
        // Given
        User mockUser = null;
        ConcertSeat mockConcertSeat = mock(ConcertSeat.class);

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            reservationService.seatReserve(mockUser, mockConcertSeat);
        });

        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("콘서트 좌석 예약 - 좌석이 null 일 때 예외 발생")
    void seatReserve_ShouldThrowException_WhenConcertSeatIsNull() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockConcertSeat = null;

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            reservationService.seatReserve(mockUser, mockConcertSeat);
        });

        Assertions.assertEquals(ErrorCode.CONCERT_SEAT_NOT_FOUND, exception.getErrorCode());
    }
}
