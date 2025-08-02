package com.mengyu.algs4.exercise.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex56_1 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        List<int[]> ans = new ArrayList<>();
        int[] range1 = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] range2 = intervals[i];
            if (range1[1] >= range2[0]) {
                range1[1] = Math.max(range1[1], range2[1]);
            } else {
                ans.add(range1);
                range1 = new int[]{range2[0], range2[1]};
            }
        }
        ans.add(range1);
        int[][] ansArr = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }
}
