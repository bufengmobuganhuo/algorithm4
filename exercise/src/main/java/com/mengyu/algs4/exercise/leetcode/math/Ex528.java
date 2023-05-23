package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex528 {
    public static void main(String[] args) {
        int[] w = {3, 14, 1, 7};
        Ex528 ex528 = new Ex528(w);
        System.out.println(ex528.pickIndex());
        System.out.println(ex528.pickIndex());
        System.out.println(ex528.pickIndex());
    }

    private int[] preSum;

    private int total;

    public Ex528(int[] w) {
        preSum = new int[w.length];
        preSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            preSum[i] = preSum[i - 1] + w[i];
        }
        total = preSum[w.length - 1];
    }

    public int pickIndex() {
        int target = (int) (Math.random() * total) + 1;
        return ceil(target);
    }

    private int ceil(int target) {
        int leftPtr = 0, rightPtr = preSum.length;
        while (leftPtr < rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            if (preSum[mid] <= target) {
                leftPtr = mid - 1;
            }else {
                rightPtr = mid;
            }
        }
        if (rightPtr - 1 >= 0 && preSum[rightPtr - 1] == target) {
            return rightPtr - 1;
        }
        return rightPtr;
    }
}
