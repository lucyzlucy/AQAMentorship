package utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public class Randomizer {
    public static int getRandomIntWithinSize(int size) {
        Random r = new Random();
        return r.nextInt(size);
    }
}
