package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex985 {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4};
        int[][] queries = {{1, 0}, {-3, 1}, {-4, 0}, {2, 3}};
        System.out.println(Arrays.toString(new Ex985().sumEvenAfterQueries(A, queries)));
    }

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int evenSum = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                evenSum += num;
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i][0], index = queries[i][1];
            if (nums[index] % 2 == 0) {
                if (val % 2 == 0) {
                    evenSum += val;
                } else {
                    evenSum -= nums[index];
                }
            } else {
                if (val % 2 != 0) {
                    evenSum += nums[index] + val;
                }
            }
            nums[index] += val;
            ans[i] = evenSum;
        }
        return ans;
    }
}
