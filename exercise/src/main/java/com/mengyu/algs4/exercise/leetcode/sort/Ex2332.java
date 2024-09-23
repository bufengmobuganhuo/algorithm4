package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2332 {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int pos = 0, space = 0;
        for (int bus : buses) {
            space = capacity;
            while (space > 0 && pos < passengers.length && passengers[pos] <= bus) {
                space--;
                pos++;
            }
        }
        pos--;
        int lastCatchTime = space > 0 ? buses[buses.length - 1] : passengers[pos];
        while (pos >= 0 && passengers[pos] == lastCatchTime) {
            pos--;
            lastCatchTime--;
        }
        return lastCatchTime;
    }
}
