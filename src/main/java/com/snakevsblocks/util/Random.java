package com.snakevsblocks.util;

/**
 * The wrapper class over java.util.Random to avoid using multiple instances of java.util.Random across th application.
 */
public class Random {

    /**
     * The Random object.
     */
    private final static java.util.Random RANDOM = new java.util.Random();

    /**
     * Get a new random integer in the range - [0, n).
     *
     * @param n the upper limit.
     * @return the random integer.
     */
    public static int nextInt(int n) {
        return RANDOM.nextInt(n);
    }

    /**
     * Get a new random double in the range - [0, 1).
     * @return the random double.
     */
    public static double nextDouble() {
        return RANDOM.nextDouble();
    }
}
