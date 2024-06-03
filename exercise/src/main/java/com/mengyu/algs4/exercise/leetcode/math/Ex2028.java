package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2028 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int targetSum = mean * (m + n);
        int curSum = 0;
        for (int roll : rolls) {
            curSum += roll;
        }
        int avg = (targetSum - curSum) / n;
        int mod = (targetSum - curSum) % n;
        if (avg > 6 || (avg == 6 && mod != 0) || avg <= 0) {
            return new int[]{};
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = avg;
            if (mod != 0) {
                ans[i]++;
                mod--;
            }
            if (ans[i] > 6) {
                return new int[]{};
            }
        }
        return ans;
    }
}
