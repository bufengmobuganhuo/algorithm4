package com.mengyu.algs4.exercise.leetcode.rank.year2023.december10;

/**
 * @date 2023/12/10 10:28
 * TODO
 */
public class Ex1 {
    public int countTestedDevices(int[] batteryPercentages) {
        int cnt = 0, delta = 0;
        for (int i = 0; i < batteryPercentages.length; i++) {
            int battery = batteryPercentages[i];
            if (battery - delta > 0) {
                cnt++;
                delta++;
            }
        }
        return cnt;
    }
}
