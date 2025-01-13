package com.mengyu.algs4.exercise.leetcode.design;

/**
 * @author yu zhang
 */
public class Ex2241 {
    private int[] values = {20, 50, 100, 200, 500};
    private int[] cnts = {0, 0, 0, 0, 0};

    public Ex2241() {

    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            this.cnts[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] ans = new int[]{0, 0, 0, 0, 0};
        for (int i = 4; i >= 0; i--) {
            ans[i] = Math.min(amount / values[i], cnts[i]);
            amount -= ans[i] * values[i];
        }
        if (amount == 0) {
            for (int i = 0; i < 5; i++) {
                this.cnts[i] -= ans[i];
            }
            return ans;
        } else {
            return new int[]{-1};
        }
    }
}
