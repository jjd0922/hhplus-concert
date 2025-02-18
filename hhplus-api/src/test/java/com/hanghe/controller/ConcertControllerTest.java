package com.hanghe.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghe.application.ConcertFacade;
import com.hanghe.interfaces.concert.dto.request.ConcertDateRequest;
import com.hanghe.interfaces.concert.dto.request.ConcertSeatRequest;
import com.hanghe.interfaces.concert.dto.response.ConcertDateResponse;
import com.hanghe.interfaces.concert.dto.response.ConcertSeatResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ConcertFacade concertFacade;

    @Test
    @DisplayName("예약 가능 날짜 조회 테스트")
    public void testPossibleDateCheck() throws Exception {
        // Given
        ConcertDateRequest request = new ConcertDateRequest(1L);
        List<ConcertDateResponse> response = Arrays.asList(
                new ConcertDateResponse(1L, LocalDate.parse("2025-02-11")),
                new ConcertDateResponse(2L,LocalDate.parse("2025-02-12"))
        );

        when(concertFacade.findAvailableConcertScheduleDate(anyLong())).thenReturn(response);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/concert/available-date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].concertScheduleId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].performanceDate").value("2025-02-11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].concertScheduleId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].performanceDate").value("2025-02-12"));
    }


    @Test
    @DisplayName("예약 가능 좌석 조회 테스트")
    public void testPossibleSeatCheck() throws Exception {
        // Given
        ConcertSeatRequest request = new ConcertSeatRequest(1L);
        List<ConcertSeatResponse> response = Arrays.asList(
                new ConcertSeatResponse(1L,1L, 10000L),
                new ConcertSeatResponse(2L,2L, 20000L)
        );

        when(concertFacade.findAvailableConcertSeat(anyLong())).thenReturn(response);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/concert/available-seat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].seatId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].seatNo").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(10000L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].seatId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].seatNo").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(20000L));

    }
}
