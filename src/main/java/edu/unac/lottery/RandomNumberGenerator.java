package edu.unac.lottery;

import java.util.Random;

public class RandomNumberGenerator {
    private Random random = new Random();

    public int generate(int max) {
        return random.nextInt(max);
    }
}
