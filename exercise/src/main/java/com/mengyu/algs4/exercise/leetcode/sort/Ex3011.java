package com.mengyu.algs4.exercise.leetcode.sort;

/**
 * @author yu zhang
 */
public class Ex3011 {
    public static void main(String[] args) {
        System.out.println(new Ex3011().canSortArray(new int[]{75, 34, 30}));
    }

    public boolean canSortArray2(int[] nums) {
        int lastCnt = 0;
        int lastCntMax = 0;
        int curCntMax = 0;
        for (int num : nums) {
            int curCnt = Integer.bitCount(num);
            if (lastCnt == curCnt) {
                curCntMax = Math.max(curCntMax, num);
            } else {
                lastCnt = curCnt;
                lastCntMax = curCntMax;
                curCntMax = num;
            }
            if (lastCntMax > num) {
                return false;
            }
        }
        return true;
    }

    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                if (!exch(nums, j, j - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean exch(int[] nums, int i, int j) {
        int cnt1 = 0, cnt2 = 0;
        int num1 = nums[i], num2 = nums[j];
        while (num1 != 0) {
            if ((num1 & 1) == 1) {
                cnt1++;
            }
            num1 >>= 1;
        }
        while (num2 != 0) {
            if ((num2 & 1) == 1) {
                cnt2++;
            }
            num2 >>= 1;
        }
        if (cnt1 != cnt2) {
            return false;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        return true;
    }
}
