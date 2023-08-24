package com.barisdev.Url.Shortener.util;

import java.security.SecureRandom;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomStringGenerator {
    private final Random random;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";

    public RandomStringGenerator() {
        this.random = new SecureRandom();
    }

    public String generateRandomString() {
        if (5 <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

}
