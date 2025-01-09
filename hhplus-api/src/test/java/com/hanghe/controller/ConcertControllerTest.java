package com.hanghe.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghe.domain.concert.entity.ConcertSchedule;
import com.hanghe.domain.concert.repository.ConcertScheduleRepository;
import com.hanghe.presentation.concert.dto.request.ConcertSeatRequest;
import com.hanghe.presentation.concert.dto.request.ConcertSeatReservationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConcertScheduleRepository concertScheduleRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("예약 가능 날짜 확인 테스트")
    void possibleDateCheckTest() throws Exception {
        // when & then
        mockMvc.perform(post("/concert/possible-date-check")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dates").isArray())
                .andDo(print());
    }

    @Test
    @DisplayName("해당 날짜 예약 가능 좌석 확인 테스트")
    void possibleSeatCheckTest() throws Exception {
        // given
        ConcertSchedule schedule = ConcertSchedule.builder()
                .id(1L)
                .performanceDate(LocalDate.now())
                .seatLimit(50)
                .build();
        concertScheduleRepository.save(schedule);

        ConcertSeatRequest request = ConcertSeatRequest.builder()
                .date(LocalDate.now())
                .build();

        // when & then
        mockMvc.perform(post("/concert/possible-seat-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatList").isNotEmpty())
                .andDo(print());
    }

    @Test
    @DisplayName("좌석 예약 테스트")
    void seatReservationTest() throws Exception {
        // given
        ConcertSeatReservationRequest request = ConcertSeatReservationRequest.builder()
                .userId(1L)
                .seatId(10L)
                .build();

        // when & then
        mockMvc.perform(post("/concert/seat/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.seatId").value(10L))
                .andDo(print());
    }
}
