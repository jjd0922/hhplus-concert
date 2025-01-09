package com.hanghe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QueueTokenControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("토큰 발행 통합 테스트")
    void createUserTokenTest() throws Exception {
        // given
        User user = User.builder()
                .id(1L)
                .name("테스트 유저")
                .build();
        userRepository.save(user);

        // when & then
        mockMvc.perform(get("/queue/token-issuance/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getId()))
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.expiredAt").isNotEmpty())
                .andDo(print());
    }
}
