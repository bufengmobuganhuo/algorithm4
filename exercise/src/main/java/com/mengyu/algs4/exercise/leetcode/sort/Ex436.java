package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex436 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 12}, {2, 9}, {3, 10}, {13, 14}, {15, 16}, {16, 17}};
        System.out.println(Arrays.toString(new Ex436().findRightInterval(intervals)));
    }

    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        Integer[] mapped = new Integer[len];
        for (int i = 0; i < len; i++) {
            mapped[i] = i;
        }
        Arrays.sort(mapped, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int[] interval1 = intervals[o1];
                int[] interval2 = intervals[o2];
                return interval1[0] - interval2[0];
            }
        });
        int[] ans = new int[len];
        for (int i = 0; i < len - 1; i++) {
            int[] interval = intervals[mapped[i]];
            int idx = search(interval[1], mapped, intervals);
            if (idx < len) {
                ans[mapped[i]] = mapped[idx];
            } else {
                ans[mapped[i]] = -1;
            }

        }
        ans[mapped[len - 1]] = -1;
        return ans;
    }

    private int search(int target, Integer[] mapped, int[][] intervals) {
        int left = 0, right = mapped.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int[] interval = intervals[mapped[mid]];
            if (interval[0] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (right - 1 >= 0 && intervals[mapped[right - 1]][0] == target) {
            return right - 1;
        }
        return right;
    }
}
