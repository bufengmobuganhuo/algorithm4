package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex3259 {
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long a1 = energyDrinkA[0], a2 = 0;
        long b1 = energyDrinkB[0], b2 = 0;
        long a = 0, b = 0;
        for (int i = 2; i < n + 1; i++) {
            a = Math.max(a1, b2) + energyDrinkA[i - 1];
            b = Math.max(b1, a2) + energyDrinkB[i - 1];
            a2 = a1;
            b2 = b1;
            a1 = a;
            b1 = b;
        }
        return Math.max(a, b);
    }
}
