package com.mengyu.algs4.exercise.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        boolean placed = false;
        int l = newInterval[0], r = newInterval[1];
        for (int[] interval : intervals) {
            if (interval[0] > r) {
                if (!placed) {
                    ans.add(new int[]{l, r});
                    placed = true;
                }
                ans.add(interval);
            } else if (interval[1] < l) {
                ans.add(interval);
            } else {
                l = Math.min(l, interval[0]);
                r = Math.max(r, interval[1]);
            }
        }
        if (!placed) {
            ans.add(new int[]{l, r});
        }
        int[][] ansArr = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }
}
