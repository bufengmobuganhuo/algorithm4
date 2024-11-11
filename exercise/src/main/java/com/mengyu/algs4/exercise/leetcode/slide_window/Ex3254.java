package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3254 {

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 2, 2};
        System.out.println(Arrays.toString(new Ex3254().resultsArray(nums, 4)));
    }

    public int[] resultsArray2(int[] nums, int k) {
        int n = nums.length;
        int cnt = 1;
        int[] ans = new int[n - k + 1];
        Arrays.fill(ans, - 1);
        for (int i = 0; i < n; i++) {
            cnt = i == 0 || nums[i] - nums[i - 1] != 1 ? 1 : cnt + 1;
            if (cnt >= k) {
                ans[i - k + 1] = nums[i];
            }
        }
        return ans;
    }

    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) {
            int[] ans = new int[n - k + 1];
            System.arraycopy(nums, 0, ans, 0, n - k + 1);
            return ans;
        }
        int leftPtr = 0, rightPtr = 1;
        int[] ans = new int[n - k + 1];
        while (rightPtr < n) {
            if (nums[rightPtr] - nums[leftPtr] != rightPtr - leftPtr) {
                while (leftPtr < rightPtr && leftPtr < n - k + 1) {
                    ans[leftPtr] = -1;
                    leftPtr++;
                }
                rightPtr++;
            } else if (rightPtr - leftPtr + 1 == k) {
                ans[leftPtr] = nums[rightPtr];
                rightPtr++;
                leftPtr++;
            } else {
                rightPtr++;
            }
        }
        return ans;
    }
}
