package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2708 {

    public static void main(String[] args) {
        int[] nums = new int[]{-9,-3,0,-7};
        System.out.println(new Ex2708().maxStrength(nums));
    }

    public long maxStrength2(int[] nums) {
        int negativeCnt = 0, zeroCnt = 0, positiveCnt = 0;
        long prod = 1;
        int negativeMax = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < 0) {
                negativeMax = Math.max(negativeMax, num);
                negativeCnt++;
                prod *= num;
            } else if (num > 0) {
                positiveCnt++;
                prod *= num;
            } else {
                zeroCnt++;
            }
        }
        if (negativeCnt == 1 && positiveCnt == 0 && zeroCnt == 0) {
            return nums[0];
        }
        if (negativeCnt <= 1 && positiveCnt == 0) {
            return 0;
        }
        if (prod < 0) {
            return prod / negativeMax;
        }
        return prod;
    }

    public long maxStrength(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        long lastNegative = Integer.MIN_VALUE, negative = Integer.MIN_VALUE;
        long lastPositive = Integer.MIN_VALUE, positive = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                break;
            } else {
                lastNegative = negative;
                negative = (negative == Integer.MIN_VALUE ? 1 : negative) * nums[i];
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > 0) {
                lastPositive = positive;
                positive = (positive == Integer.MIN_VALUE ? 1 : positive) * nums[i];
            } else if (nums[i] == 0) {
                lastPositive = positive;
                positive = 0;
                break;
            } else {
                break;
            }
        }
        if (negative > 0 && positive > 0) {
            return negative * positive;
        } else if (negative < 0 && positive > 0) {
            return (lastNegative > 0 ? lastNegative : 1) * positive;
        } else if (negative < 0 && positive == 0) {
            if (lastNegative > 0) {
                return lastNegative * (lastPositive > 0 ? lastPositive : 1);
            } else {
                return lastPositive > 0 ? lastPositive : positive;
            }
        } else if (negative > 0 && positive == 0) {
            return negative * (lastPositive > 0 ? lastPositive : 1);
        } else if (positive < 0) {
            return negative > 0 ? negative : (lastNegative > 0 ? lastNegative : negative);
        }
        return -1L;
    }
}
