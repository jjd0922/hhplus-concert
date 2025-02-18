package com.hanghe.controller;

import com.hanghe.application.UserFacade;

import com.hanghe.domain.user.service.dto.UserBalanceDTO;
import com.hanghe.interfaces.user.dto.response.UserBalanceResponse;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    @Test
    @DisplayName("사용자 잔액 조회 통합 테스트")
    void selectUserBalanceTest() throws Exception {
        // Given
        Long userId = 1L;
        UserBalanceResponse response = UserBalanceResponse.from(new UserBalanceDTO(userId,1000L));

        when(userFacade.findUserBalance(anyLong())).thenReturn(response);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/balance/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000L));
    }

}
