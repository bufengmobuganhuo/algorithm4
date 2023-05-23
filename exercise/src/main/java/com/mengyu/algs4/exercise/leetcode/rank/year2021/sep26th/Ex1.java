package com.mengyu.algs4.exercise.leetcode.rank.year2021.sep26th;

/**
 * @author yuzhang
 * @date 2021/12/26 10:30 上午
 * TODO
 */
public class Ex1 {
    public boolean isSameAfterReversals(int num) {
        String numStr = String.valueOf(num);
        int count1 = 0;
        int count2 = 0;
        int idx = numStr.length() - 1;
        while (idx >= 0 && numStr.charAt(idx) == '0') {
            count1++;
            idx--;
        }
        for (int i = idx; i >= 0; i--) {
            if (numStr.charAt(i) != '0') {
                count2++;
            }
        }
        return !(count2 > 0 && count1 > 0);
    }
}
