package com.orakul0187.producer;

import java.time.LocalDateTime;


public class Rand {

    public static int randomInt(int begin, int end) {
        return (int) (begin + Math.random() * (end - begin + 1));
    }

    public static long randomLong(long begin, long end) {
        return (long) (begin + Math.random() * (end - begin + 1));
    }

    public static double randomDouble(double begin, double end) {
        return (double) ((int) (begin + Math.random() * (end * 10 - begin * 10 + 1))) / 10;
    }

    public static LocalDateTime randomLocalDateTime(int begin, int end) {
        LocalDateTime now = LocalDateTime.now();
        int year = 60 * 60 * 24 * 365;
        return now.plusSeconds((long) randomInt((begin - now.getYear()) * year, (end - now.getYear()) * year));
    }

    public static LocalDateTime randomLocalDateTime(LocalDateTime begin, int shift) {
        int year = 60 * 60 * 24 * 365;
        return begin.plusSeconds(randomLong(1, year * 2));
    }
}
