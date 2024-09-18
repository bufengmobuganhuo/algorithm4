package com.mengyu.algs4.exercise.leetcode.enumration;

/**
 * @author yu zhang
 */
public class Ex2552 {
    /**
     * 要求的是：0 <= i < j < k < l，且nums[i] < nums[k] < nums[j] < nums[l]
     *
     * 1. 枚举 nums[k]和nums[j]，即得到nums[i] < nums[k]的元祖数量x1，nums[j] < nums[l]的元祖数量x2，则最终答案=x1 * x2
     */
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        // pre[x]：从nums[0]~nums[j - 1]中，小于x的元素数量
        int[] pre = new int[n + 1];
        long ans = 0;
        for (int j = 0; j < n; j++) {
            // 从nums[n - 1] ~ nums[j - 1]中，大于nums[j]的数量
            int suf = 0;
            for (int k = n - 1; k > j; k--) {
                // 能组成四元组
                if (nums[j] > nums[k]) {
                    // nums[j]是固定的，pre[nums[k]]就是满足要求的nums[i]的数量，即前半部分
                    // suf是后半部分
                    ans += (long) pre[nums[k]] * suf;
                } else {
                    // 从右到左遍历，记录大于nums[j]的数量
                    suf++;
                }
            }
            // j是从0开始遍历的，这里更新从nums[0]~nums[j - 1](下次j更新时的数量)中，小于x的数量
            for (int x = nums[j] + 1; x < n + 1; x++) {
                pre[x]++;
            }
        }
        return ans;
    }
}
