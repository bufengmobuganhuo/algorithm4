package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex646 {

    public static void main(String[] args) {
        int[][] pairs = {{1,2}, {2,3}, {3,4}};
        System.out.println(new Ex646().findLongestChain3(pairs));
    }

    public int findLongestChain3(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int cur = Integer.MIN_VALUE, res = 0;
        for (int[] pair : pairs) {
            if (cur < pair[0]) {
                cur = pair[1];
                res++;
            }
        }
        return res;
    }

    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        List<Integer> dp = new ArrayList<>(pairs.length);
        for (int[] pair : pairs) {
            int x = pair[0], y = pair[1];
            if (dp.isEmpty() || dp.get(dp.size() - 1) < x) {
                dp.add(y);
            } else {
                int idx = floor(dp, x);
                dp.set(idx, Math.min(dp.get(idx), y));
            }
        }
        return dp.size();
    }

    private int floor(List<Integer> list, int target) {
        int leftPtr = 0, rightPtr = list.size();
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            if (list.get(midPtr) == target) {
                return midPtr;
            } else if (list.get(midPtr) < target) {
                leftPtr = midPtr + 1;
            } else {
                rightPtr = midPtr - 1;
            }
        }
        return leftPtr;
    }

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int len = pairs.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            int[] pair1 = pairs[i];
            for (int j = 0; j < i; j++) {
                int[] pair2 = pairs[j];
                if (pair2[1] < pair1[0]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[len - 1];
    }
}
