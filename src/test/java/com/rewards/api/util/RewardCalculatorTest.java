package com.rewards.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for RewardCalculator.
 */
public class RewardCalculatorTest {

    @Test
    void shouldReturnZeroPointsForAmountLessThan50() {
        assertEquals(0, RewardCalculator.calculatePoints(40));
    }

    @Test
    void shouldReturnZeroPointsForAmountEqualTo50() {
        assertEquals(0, RewardCalculator.calculatePoints(50));
    }

    @Test
    void shouldReturn25PointsFor75Dollars() {
        assertEquals(25, RewardCalculator.calculatePoints(75));
    }

    @Test
    void shouldReturn50PointsFor100Dollars() {
        assertEquals(50, RewardCalculator.calculatePoints(100));
    }

    @Test
    void shouldReturn90PointsFor120Dollars() {
        assertEquals(90, RewardCalculator.calculatePoints(120));
    }

    @Test
    void shouldReturn150PointsFor150Dollars() {
        assertEquals(150, RewardCalculator.calculatePoints(150));
    }
}