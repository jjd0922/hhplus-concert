package com.hanghe.domain.concert;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.service.ConcertService;
import com.hanghe.domain.concert.service.dto.ConcertScheduleDTO;
import com.hanghe.domain.concert.service.dto.ConcertSeatDTO;
import com.hanghe.infrastructure.concert.schedule.ConcertScheduleRepositoryImpl;
import com.hanghe.infrastructure.concert.seat.ConcertSeatRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {

    @InjectMocks
    private ConcertService concertService;

    @Mock
    private ConcertScheduleRepositoryImpl concertScheduleRepositoryImpl;

    @Mock
    private ConcertSeatRepositoryImpl concertSeatRepositoryImpl;

    @Test
    @DisplayName("콘서트 예약 가능 날짜 정상 조회")
    void findAllConcertScheduleDate_ShouldReturnScheduleDates_WhenValidConcertId() {
        // Given
        Long concertId = 1L;
        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusMonths(1);
        List<ConcertScheduleDTO> mockSchedules = List.of(
                new ConcertScheduleDTO(concertId,today),
                new ConcertScheduleDTO(concertId,nextMonth)
        );
        when(concertScheduleRepositoryImpl.findAvailableDatesByConcertIdWithinRange(concertId, today, nextMonth))
                .thenReturn(mockSchedules);

        // When
        List<ConcertScheduleDTO> result = concertService.findAllConcertScheduleDate(concertId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        verify(concertScheduleRepositoryImpl, times(1))
                .findAvailableDatesByConcertIdWithinRange(concertId, today, nextMonth);
    }

    @Test
    @DisplayName("콘서트 예약 가능 날짜 조회: concertId가 null일 때 예외 발생")
    void findAllConcertScheduleDate_ShouldThrowException_WhenConcertIdIsNull() {
        // Given
        Long concertId = null;

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            concertService.findAllConcertScheduleDate(concertId);
        });

        Assertions.assertEquals(ErrorCode.CONCERT_NOT_FOUND, exception.getErrorCode());
        verifyNoInteractions(concertScheduleRepositoryImpl);
    }

    @Test
    @DisplayName("콘서트 예약 가능 좌석 정상 조회")
    void findAllConcsertScheduleSeat_ShouldReturnAvailableSeats_WhenValidScheduleId() {
        // Given
        Long concertScheduleId = 1L;
        List<ConcertSeatDTO> mockSeats = List.of(
                new ConcertSeatDTO(concertScheduleId, 1L, 10000L),
                new ConcertSeatDTO(concertScheduleId, 2L, 20000L)
        );
        when(concertSeatRepositoryImpl.findAvailableSeatsByConcertScheduleId(concertScheduleId))
                .thenReturn(mockSeats);

        // When
        List<ConcertSeatDTO> result = concertService.findAllConcsertScheduleSeat(concertScheduleId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        verify(concertSeatRepositoryImpl, times(1))
                .findAvailableSeatsByConcertScheduleId(concertScheduleId);
    }

    @Test
    @DisplayName("콘서트 예약 가능 좌석 조회: concertScheduleId가 null일 때 예외 발생")
    void findAllConcsertScheduleSeat_ShouldThrowException_WhenScheduleIdIsNull() {
        // Given
        Long concertScheduleId = null;

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            concertService.findAllConcsertScheduleSeat(concertScheduleId);
        });

        Assertions.assertEquals(ErrorCode.CONCERT_SCEDULE_NOT_FOUND, exception.getErrorCode());
        verifyNoInteractions(concertSeatRepositoryImpl);
    }
}
