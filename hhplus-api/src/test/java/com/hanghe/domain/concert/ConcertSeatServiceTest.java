package com.hanghe.domain.concert;

import com.hanghe.common.exception.BusinessException;
import com.hanghe.common.exception.ErrorCode;
import com.hanghe.domain.concert.entity.ConcertSeat;
import com.hanghe.domain.concert.repository.ConcertSeatRepository;
import com.hanghe.domain.concert.service.ConcertSeatService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ConcertSeatServiceTest {

    @InjectMocks
    private ConcertSeatService concertSeatService;

    @Mock
    private ConcertSeatRepository concertSeatRepository;

    @Test
    @DisplayName("좌석 정상 조회")
    void findSeat_ShouldReturnConcertSeat_WhenSeatExists() {
        // Given
        Long seatId = 1L;
        ConcertSeat mockSeat = mock(ConcertSeat.class);
        when(concertSeatRepository.findById(seatId)).thenReturn(mockSeat);

        // When
        ConcertSeat result = concertSeatService.findSeat(seatId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockSeat, result);
        verify(concertSeatRepository, times(1)).findById(seatId);
    }

    @Test
    @DisplayName("좌석 조회 - 좌석이 존재하지 않을 때 예외 발생")
    void findSeat_ShouldThrowEntityNotFoundException_WhenSeatDoesNotExist() {
        // Given
        Long seatId = 1L;
        when(concertSeatRepository.findById(seatId)).thenThrow(new BusinessException(ErrorCode.CONCERT_SEAT_NOT_FOUND));

        // When & Then
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            concertSeatService.findSeat(seatId);
        });

        Assertions.assertEquals(ErrorCode.CONCERT_SEAT_NOT_FOUND, exception.getErrorCode());
        verify(concertSeatRepository, times(1)).findById(seatId);
    }
}
