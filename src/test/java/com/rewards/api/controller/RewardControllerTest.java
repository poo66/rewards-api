package com.rewards.api.controller;

import com.rewards.api.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller integration test.
 */
@WebMvcTest(RewardController.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RewardService rewardService;

    @Test
    void shouldReturn200WhenCustomerExists() throws Exception {

        when(rewardService.getRewardsByCustomer(101L))
                .thenReturn(null);

        mockMvc.perform(get("/api/rewards/101"))
                .andExpect(status().isOk());
    }
}