package com.example.biggernumbergame.helpers;

public class MathHelper {

    public static int generateRandomNumber(int upper, int lower) {
        return (int) (Math.random() * (upper - lower)) + lower;
    }
}
