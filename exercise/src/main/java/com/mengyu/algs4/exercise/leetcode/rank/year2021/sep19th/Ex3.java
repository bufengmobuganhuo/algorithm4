package com.mengyu.algs4.exercise.leetcode.rank.year2021.sep19th;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/12/19 10:35 上午
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] prices = {3,2,1,4,3,2,1};
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.getDescentPeriods(prices));
    }
    public long getDescentPeriods(int[] prices) {
        List<Long> countLengths = new ArrayList<>();
        int pre = prices[0];
        long length = 1;
        for (int i = 1; i < prices.length; i++) {
            if (pre - prices[i] == 1) {
                length++;
                pre = prices[i];
            } else {
                countLengths.add(length);
                pre = prices[i];
                length = 1;
            }
        }
        if (length != 1) {
            countLengths.add(length);
        }
        long result = 0;
        for (long len : countLengths) {
            for (int i = 2; i <= len; i++) {
                result += (len - i + 1);
            }
        }
        result += prices.length;
        return result;
    }
}
