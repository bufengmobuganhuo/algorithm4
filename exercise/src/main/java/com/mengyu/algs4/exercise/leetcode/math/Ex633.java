package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex633 {
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a < c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        // 假定a <= b，如下是a,b分别能取到的最小/最大值
        long a = 0, b = (int) Math.sqrt(c);
        while (a <= b) {
            long mid = a * a + b * b;
            if (mid == c) {
                return true;
            } else if (mid > c) {
                // 和过大，则减小b
                b--;
            } else {
                // 和太小，增大a
                a++;
            }
        }
        return false;
    }
}
