package com.mengyu.algs4.exercise.leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex18904 {
    public static void main(String[] args) {
        int[] chalk = {3,4,1,2};
        System.out.println(new Ex18904().chalkReplacer(chalk, 25));
    }
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        if (chalk[0] > k) {
            return 0;
        }
        for (int i = 1; i < len; i++) {
            chalk[i] += chalk[i - 1];
            if (chalk[i] > k) {
                return i;
            }
        }
        // 走到这里说明chalk的和一定>k，所以需要走几圈，chalk[len] % k
        k %= chalk[len - 1];
        return floor(chalk, k) + 1;
    }

    private int floor(int[] arr, int target) {
        int len = arr.length, leftPtr = -1, rightPtr = len - 1;
        while (leftPtr < rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
            if (target <= arr[mid]) {
                // 求的是<=target的最大值，那如果arr[mid] > target肯定不满足，则-1，下面会有判断=target的情况
                rightPtr = mid - 1;
            } else {
                leftPtr = mid;
            }
        }
        // =target
        if (leftPtr + 1 < len && arr[leftPtr + 1] == target) {
            return leftPtr + 1;
        }
        // 找到<target
        return leftPtr;
    }
}
