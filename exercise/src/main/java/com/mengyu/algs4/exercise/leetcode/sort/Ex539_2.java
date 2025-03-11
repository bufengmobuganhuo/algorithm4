package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Collections;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex539_2 {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        boolean flag = false;
        int ans = Integer.MAX_VALUE;
        int n = timePoints.size();
        for (int i = 0; i <= timePoints.size(); i++) {
            String time1 = timePoints.get(i % n);
            String time2 = timePoints.get((i + 1) % n);
            int minutes1 =
                    ((time1.charAt(0) - '0') * 10 + time1.charAt(1) - '0') * 60 +
                            (time1.charAt(3) - '0') * 10 + time1.charAt(4) - '0';
            int minutes2 =
                    ((time2.charAt(0) - '0') * 10 + time2.charAt(1) - '0') * 60 +
                            (time2.charAt(3) - '0') * 10 + time2.charAt(4) - '0';
            if (minutes1 > minutes2) {
                ans = Math.min(ans, minutes2 + 24 * 60 - minutes2);
            } else {
                ans = Math.min(ans, minutes2 - minutes1);
            }
        }
        return ans;
    }
}
