package com.rewards.api.service;

import com.rewards.api.exception.ResourceNotFoundException;
import com.rewards.api.model.RewardResponse;
import com.rewards.api.model.Transaction;
import com.rewards.api.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit test for RewardService.
 */
@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardService rewardService;

    @Test
    void shouldCalculateRewardsSuccessfully() {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(101L, "John", 120.0, LocalDate.of(2026, 1, 15)),
                new Transaction(101L, "John", 75.0, LocalDate.of(2026, 2, 10)),
                new Transaction(101L, "John", 150.0, LocalDate.of(2026, 3, 5))
        );

        when(transactionRepository.findByCustomerId(101L))
                .thenReturn(transactions);

        RewardResponse response = null;
		try {
			response = rewardService.getRewardsByCustomer(101L);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

        assertEquals(101L, response.getCustomerId());
        assertEquals("John", response.getCustomerName());
        assertEquals(265, response.getTotalRewards());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        when(transactionRepository.findByCustomerId(999L))
                .thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class,
                () -> rewardService.getRewardsByCustomer(999L));
    }
}