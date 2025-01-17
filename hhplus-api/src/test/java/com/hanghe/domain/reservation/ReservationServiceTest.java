package com.hanghe.domain.reservation;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.entity.ReservationStatus;
import com.hanghe.domain.reservation.repository.ReservationRepository;
import com.hanghe.domain.reservation.service.ReservationService;
import com.hanghe.domain.user.entity.User;
import com.hanghe.interfaces.reservation.dto.response.ReservationResponce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("콘서트 좌석 정상 예약")
    void seatReserve_ShouldSaveReservation_WhenValidRequest() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockConcertSeat = mock(ConcertSeat.class);
        Reservation mockReservation = mock(Reservation.class);

        try (MockedStatic<Reservation> mockedReservation = mockStatic(Reservation.class)) {
            mockedReservation.when(() -> Reservation.reserve(mockUser, mockConcertSeat))
                    .thenReturn(mockReservation);

            when(reservationRepository.save(mockReservation)).thenReturn(mockReservation);
            when(mockReservation.getId()).thenReturn(1L);
            when(mockReservation.getStatus()).thenReturn(ReservationStatus.WAIT);
            when(mockReservation.getReservedAt()).thenReturn(LocalDateTime.now());
            when(mockReservation.getExpiredAt()).thenReturn(LocalDateTime.now().plusMinutes(30));

            // When
            ReservationResponce response = reservationService.seatReserve(mockUser, mockConcertSeat);

            // Then
            Assertions.assertNotNull(response);
            Assertions.assertEquals(1L, response.getReservationId());
            Assertions.assertEquals("WAIT", response.getStatus());
            Assertions.assertNotNull(response.getReservedAt());
            Assertions.assertNotNull(response.getExpiredAt());
            verify(reservationRepository, times(1)).save(mockReservation);
        }
    }

    @Test
    @DisplayName("콘서트 좌석 예약 - 유저가 null일 때 예외 발생")
    void seatReserve_ShouldThrowException_WhenUserIsNull() {
        // Given
        User mockUser = null;
        ConcertSeat mockConcertSeat = mock(ConcertSeat.class);

        // When & Then
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            reservationService.seatReserve(mockUser, mockConcertSeat);
        });

        Assertions.assertEquals("User가 null입니다.", exception.getMessage());
        verifyNoInteractions(reservationRepository);
    }

    @Test
    @DisplayName("콘서트 좌석 예약 - 좌석이 null일 때 예외 발생")
    void seatReserve_ShouldThrowException_WhenConcertSeatIsNull() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockConcertSeat = null;

        // When & Then
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            reservationService.seatReserve(mockUser, mockConcertSeat);
        });

        Assertions.assertEquals("ConcertSeat가 null입니다.", exception.getMessage());
        verifyNoInteractions(reservationRepository);
    }
}
