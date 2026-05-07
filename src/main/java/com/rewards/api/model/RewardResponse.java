package com.rewards.api.model;

import java.util.Map;

/**
 * DTO for reward response.
 */
public class RewardResponse {

    private Long customerId;
    private String customerName;
    private Map<String, Integer> monthlyRewards;
    private Integer totalRewards;

    public RewardResponse(Long customerId, String customerName,
                          Map<String, Integer> monthlyRewards,
                          Integer totalRewards) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public Integer getTotalRewards() {
        return totalRewards;
    }
}
