package com.rewards.api.service;


import com.rewards.api.exception.ResourceNotFoundException;
import com.rewards.api.model.RewardResponse;
import com.rewards.api.model.Transaction;
import com.rewards.api.repository.TransactionRepository;
import com.rewards.api.util.RewardCalculator;
import org.springframework.stereotype.Service;

import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Service for reward calculations.
 */
@Service
public class RewardService {

    private final TransactionRepository transactionRepository;

    public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public RewardResponse getRewardsByCustomer(Long customerId) throws ResourceNotFoundException {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("Customer not found");
        }

        Map<String, Integer> monthlyRewards = new HashMap<>();
        int totalRewards = 0;
        String customerName = transactions.get(0).getCustomerName();

        for (Transaction transaction : transactions) {
            int points = RewardCalculator.calculatePoints(transaction.getAmount());

            String month = transaction.getTransactionDate()
                    .getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            monthlyRewards.put(month,
                    monthlyRewards.getOrDefault(month, 0) + points);

            totalRewards += points;
        }

        return new RewardResponse(
                customerId,
                customerName,
                monthlyRewards,
                totalRewards
        );
    }
}
