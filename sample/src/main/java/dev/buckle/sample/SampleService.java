package dev.buckle.sample;

import java.util.Random;

public class SampleService {
    private final Random random = new Random();

    public int getRandomNumber() {
        return random.nextInt();
    }
}
