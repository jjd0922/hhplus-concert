package com.hanghe.domain.reservation;

import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.reservation.entity.Reservation;
import com.hanghe.domain.reservation.entity.ReservationStatus;
import com.hanghe.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ReservationTest {

    @Test
    @DisplayName("좌석 정상 예약")
    void reserve_ShouldCreateReservation_WhenValidInput() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockSeat = mock(ConcertSeat.class);

        // When
        Reservation reservation = Reservation.reserve(mockUser, mockSeat);

        // Then
        Assertions.assertNotNull(reservation);
        Assertions.assertEquals(mockUser, reservation.getUser());
        Assertions.assertEquals(mockSeat, reservation.getConcertSeat());
        Assertions.assertEquals(ReservationStatus.WAIT, reservation.getStatus());
        Assertions.assertNotNull(reservation.getReservedAt());
        Assertions.assertNotNull(reservation.getExpiredAt());
        Assertions.assertTrue(reservation.getExpiredAt().isAfter(reservation.getReservedAt()));
    }

    @Test
    @DisplayName("예약 완료 정상 처리")
    void complete_ShouldUpdateStatusToComplete_WhenReservationIsInWaitStatus() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockSeat = mock(ConcertSeat.class);
        Reservation reservation = Reservation.reserve(mockUser, mockSeat);

        // When
        reservation.complete();

        // Then
        Assertions.assertEquals(ReservationStatus.COMPLETE, reservation.getStatus());
    }

    @Test
    @DisplayName("예약 완료 처리 - 이미 완료된 예약에 대해 예외 발생")
    void complete_ShouldThrowException_WhenReservationIsAlreadyComplete() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockSeat = mock(ConcertSeat.class);
        Reservation reservation = Reservation.reserve(mockUser, mockSeat);
        reservation.complete();

        // When & Then
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, reservation::complete);
        Assertions.assertEquals("이미 예약 완료된 좌석입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("예약 취소 정상 처리")
    void cancel_ShouldUpdateStatusToCancel_WhenReservationIsInWaitStatus() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockSeat = mock(ConcertSeat.class);
        Reservation reservation = Reservation.reserve(mockUser, mockSeat);

        // When
        reservation.cancel();

        // Then
        Assertions.assertEquals(ReservationStatus.CANCEL, reservation.getStatus());
    }

    @Test
    @DisplayName("예약 취소 처리 - 이미 취소된 예약에 대해 예외 발생")
    void cancel_ShouldThrowException_WhenReservationIsAlreadyCancelled() {
        // Given
        User mockUser = mock(User.class);
        ConcertSeat mockSeat = mock(ConcertSeat.class);
        Reservation reservation = Reservation.reserve(mockUser, mockSeat);
        reservation.cancel();

        // When & Then
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, reservation::cancel);
        Assertions.assertEquals("이미 취소된 좌석입니다.", exception.getMessage());
    }
}
