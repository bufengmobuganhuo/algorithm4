package com.mengyu.algs4.exercise.leetcode.binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author yu zhang
 */
public class Ex3152 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 2};
        int[][] queries = {
                {1, 2}
        };
        System.out.println(Arrays.toString(new Ex3152().isArraySpecial2(nums, queries)));
    }

    public boolean[] isArraySpecial3(int[] nums, int[][] queries) {
        int n = nums.length;
        boolean expectedOdd = nums[0] % 2 == 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            if ((expectedOdd && nums[i] % 2 != 0) || (!expectedOdd && nums[i] % 2 == 0)) {
                dp[i] = dp[i - 1] + 1;
                expectedOdd = !expectedOdd;
            }
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0], to = queries[i][1];
            ans[i] = dp[to] >= (to - from + 1);
        }
        return ans;
    }

    public boolean[] isArraySpecial2(int[] nums, int[][] queries) {
        int leftPtr = 0, rightPtr = 1, n = nums.length;
        boolean expectedOdd = nums[0] % 2 == 0;
        List<int[]> ranges = new ArrayList<>();
        while (true) {
            if (rightPtr >= n) {
                ranges.add(new int[]{leftPtr, rightPtr - 1});
                break;
            }
            if ((expectedOdd && nums[rightPtr] % 2 != 0) || (!expectedOdd && nums[rightPtr] % 2 == 0)) {
                rightPtr++;
                expectedOdd = !expectedOdd;
            } else {
                ranges.add(new int[]{leftPtr, rightPtr - 1});
                leftPtr = rightPtr;
                rightPtr = leftPtr + 1;
            }
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0], to = queries[i][1];
            if (from == to) {
                ans[i] = true;
                continue;
            }
            int idx = floor(ranges, from);
            if (idx >= 0 && idx < ranges.size()) {
                ans[i] = ranges.get(idx)[1] >= to;
            } else {
                ans[i] = false;
            }
        }
        return ans;
    }

    private int floor(List<int[]> ranges, int target) {
        int leftPtr = -1, rightPtr = ranges.size() - 1;
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr + 1) / 2;
            if (ranges.get(midPtr)[0] >= target) {
                rightPtr = midPtr - 1;
            } else {
                leftPtr = midPtr;
            }
        }
        if (leftPtr < ranges.size() - 1 && ranges.get(leftPtr + 1)[0] == target) {
            return leftPtr + 1;
        }
        return leftPtr;
    }

    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(o -> o[0]));
        int leftPtr = 0, rightPtr = 1, n = nums.length;
        boolean expectOdd = nums[0] % 2 == 0;
        while (true) {
            if (rightPtr >= n) {
                set.add(new int[]{leftPtr, rightPtr - 1});
                break;
            }
            if ((expectOdd && nums[rightPtr] % 2 != 0) || (!expectOdd && nums[rightPtr] % 2 == 0)) {
                rightPtr++;
                expectOdd = !expectOdd;
            } else {
                set.add(new int[]{leftPtr, rightPtr - 1});
                leftPtr = rightPtr;
                rightPtr = leftPtr + 1;
            }
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == queries[i][1]) {
                ans[i] = true;
                continue;
            }
            int[] floor = set.floor(queries[i]);
            if (floor != null) {
                ans[i] = floor[1] >= queries[i][1];
            }
        }
        return ans;
    }
}
