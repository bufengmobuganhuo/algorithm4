package com.mengyu.algs4.exercise.leetcode.math;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex204 {

    public static void main(String[] args) {
        System.out.println(new Ex204().countPrimes2(10));
    }

    /**
     * 埃式筛选，如果一个数是质数，那2x, 3x都不是质数
     *
     */
    public int countPrimes2(int n) {
        // isPrime[x] = true：数字x是质数
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int cnt = 0;
        for (int x = 2; x < n; x++) {
            if (isPrime[x]) {
                cnt++;
                // 实际上只需要从 x*x开始标记即可，比如对于x=2，那一定已经标记了2x=4, 3x=6...，则遍历到x'=3时，不需要再标记2x'=6
                for (long j = (long) x * x; j < n; j += x) {
                    isPrime[(int)j] = false;
                }
            }
        }
        return cnt;
    }

    public int countPrimes(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
