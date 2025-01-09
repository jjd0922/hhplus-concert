package com.hanghe.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import com.hanghe.presentation.payment.dto.request.PaymentChargeRequest;
import com.hanghe.presentation.payment.dto.request.PaymentUseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("결제 충전 통합 테스트")
    void paymentChargeTest() throws Exception {
        // given
        User user = User.builder()
                .id(1L)
                .name("테스트 유저")
                .balance(0)
                .build();
        userRepository.save(user);

        PaymentChargeRequest request = PaymentChargeRequest.builder()
                .userId(user.getId())
                .balance(5000)
                .build();

        // when & then
        mockMvc.perform(post("/payment/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getId()))
                .andExpect(jsonPath("$.balance").value(5000))
                .andDo(print());
    }

    @Test
    @DisplayName("결제 사용 통합 테스트")
    void paymentUseTest() throws Exception {
        // given
        User user = User.builder()
                .id(1L)
                .name("테스트 유저")
                .balance(5000)
                .build();
        userRepository.save(user);

        PaymentUseRequest request = PaymentUseRequest.builder()
                .userId(user.getId())
                .balance(2000)
                .build();

        // when & then
        mockMvc.perform(post("/payment/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getId()))
                .andExpect(jsonPath("$.balance").value(3000))
                .andDo(print());
    }
}
