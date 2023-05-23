package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yuzhang
 * @date 2021/1/18 下午2:30
 * TODO
 */
public class Ex121 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else if (prices[i] - min > res) {
                res = prices[i] - min;
            }
        }
        return res;
    }
}
