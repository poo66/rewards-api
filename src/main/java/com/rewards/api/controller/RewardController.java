package com.rewards.api.controller;


import com.rewards.api.exception.ResourceNotFoundException;
import com.rewards.api.model.RewardResponse;
import com.rewards.api.service.RewardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for rewards API.
 */
@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    public RewardResponse getRewards(@PathVariable Long customerId) {
        try {
			return rewardService.getRewardsByCustomer(customerId);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    }
}
