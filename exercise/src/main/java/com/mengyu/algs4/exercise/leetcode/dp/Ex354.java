package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex354 {

    public static void main(String[] args) {
        int[][] envelopes = {{15,8},{2,20},{2,14},{4,17},{8,19},{8,9},{5,7},{11,19},{8,11},{13,11},{2,13},{11,19},{8,11},{13,11},{2,13},{11,19},{16,1},{18,13},{14,17},{18,19}};
        System.out.println(new Ex354().maxEnvelopes(envelopes));
    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        int n = envelopes.length;
        int[] dp = new int[n + 1];
        dp[1] = envelopes[0][1];
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            int[] envelope = envelopes[i];
            if (envelope[1] > dp[maxLen]) {
                dp[++maxLen] = envelope[1];
            } else {
                int leftPtr = 1, rightPtr = maxLen, target = envelope[1];
                while (leftPtr < rightPtr) {
                    int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
                    if (dp[midPtr] < target) {
                        leftPtr = midPtr + 1;
                    } else {
                        rightPtr = midPtr;
                    }
                }
                dp[leftPtr] = target;


            }
        }
        return maxLen;
    }

    private int floor(int[][] arr, int target, int leftPtr, int rightPtr) {
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            if (arr[midPtr][0] == target) {
                return midPtr;
            } else if (arr[midPtr][0] < target) {
                leftPtr = midPtr + 1;
            } else {
                rightPtr = midPtr - 1;
            }
        }
        return rightPtr;
    }
}
