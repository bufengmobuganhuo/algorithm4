package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex202 {
    public boolean isHappy(int n) {
        int slow = n, fast = getSum(n);
        while (fast != 1 && fast != slow) {
            slow = getSum(slow);
            fast = getSum(fast);
            fast = getSum(fast);
        }
        return slow == 1;
    }

    private int getSum(int n) {
        int sum = 0;
        while (n != 0) {
            int bit = n % 10;
            n /= 10;
            sum += bit * bit;
        }
        return sum;
    }
}
