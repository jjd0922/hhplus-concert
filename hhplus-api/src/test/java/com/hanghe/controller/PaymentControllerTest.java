package com.hanghe.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghe.application.PaymentFacade;
import com.hanghe.domain.user.entity.User;
import com.hanghe.domain.user.repository.UserRepository;
import com.hanghe.domain.user.service.UserService;
import com.hanghe.interfaces.payment.dto.request.PaymentChargeRequest;
import com.hanghe.interfaces.payment.dto.request.PaymentUseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("결제 충전 통합 테스트")
    void paymentChargeTest() throws Exception {
        // given
        Long userId = 1L;
        Long amount = 5000L;
        PaymentChargeRequest request = new PaymentChargeRequest(userId, amount);

        // when & then
        mockMvc.perform(post("/payment/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(amount))
                .andExpect(jsonPath("$.balance").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("결제 사용 통합 테스트")
    void paymentUseTest() throws Exception {
        // given
        Long userId = 1L;
        UUID uuid = UUID.randomUUID();
        PaymentUseRequest request = new PaymentUseRequest(userId,uuid.toString(),1L,2000L);

        User mockUser = new User(userId, "테스트 유저", 5000L);
        when(userService.findUser(userId)).thenReturn(mockUser);

        // when & then
        mockMvc.perform(post("/payment/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.balance").value(3000L))
                .andDo(print());
    }
}
