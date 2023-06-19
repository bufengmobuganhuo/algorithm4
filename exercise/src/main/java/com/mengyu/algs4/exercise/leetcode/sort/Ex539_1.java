package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex539_1 {
    public static void main(String[] args) {
        List<String> timePoints = Arrays.asList("00:00", "23:59");
        System.out.println(new Ex539_1().findMinDifference(timePoints));
    }

    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int t0Minutes = getMinutes(timePoints.get(0));
        int preMinutes = t0Minutes;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); i++) {
            int minutes = getMinutes(timePoints.get(i));
            ans = Math.min(ans, minutes - preMinutes);
            preMinutes = minutes;
        }
        ans = Math.min(ans, t0Minutes + 1440 - preMinutes);
        return ans;
    }

    public int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + t.charAt(1) - '0') * 60 + (t.charAt(3) - '0') * 10 + t.charAt(4) - '0';
    }
}
