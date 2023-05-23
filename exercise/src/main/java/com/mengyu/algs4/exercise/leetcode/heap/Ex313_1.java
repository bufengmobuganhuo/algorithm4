package com.mengyu.algs4.exercise.leetcode.heap;

/**
 * @author yu zhang
 */
public class Ex313_1 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglies = new int[n];
        uglies[0] = 1;
        int[] pointers = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int j = 0; j < pointers.length; j++) {
                if (uglies[pointers[j]] * primes[j] < min) {
                    min = uglies[pointers[j]] * primes[j];
                    minIdx = j;
                } else if (uglies[pointers[j]] * primes[j] == min) {
                    pointers[j]++;
                }
            }
            pointers[minIdx]++;
            uglies[i] = min;
        }
        return uglies[n - 1];
    }
}
