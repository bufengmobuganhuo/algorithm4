package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex3115 {

    private final Set<Integer> primes = new HashSet<>();

    public Ex3115() {
        for (int i = 1; i < 101; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
    }

    public int maximumPrimeDifference(int[] nums) {
        int first = -1, last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (primes.contains(nums[i])) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return last - first;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
