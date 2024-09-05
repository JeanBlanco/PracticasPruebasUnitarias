package edu.unac.lottery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomNumberGeneratorTest {
    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    void setUp() {
        randomNumberGenerator = new RandomNumberGenerator();
    }

    @Test
    void whenGenerateIsCalledThenReturnsNumberWithinRange() {
        int max = 5;
        int result = randomNumberGenerator.generate(max);
        assertTrue(result >= 0 && result <= max);
    }

    @Test
    void whenGenerateIsCalledWithZeroThenReturnsZero() {
        int result = randomNumberGenerator.generate(1);
        assertEquals(0, result);
    }

    @Test
    void whenGenerateIsCalledWithNegativeMaxThenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            randomNumberGenerator.generate(-1);
        });
    }
}