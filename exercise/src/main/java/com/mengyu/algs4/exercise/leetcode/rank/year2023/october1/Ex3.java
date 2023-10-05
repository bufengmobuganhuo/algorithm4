package com.mengyu.algs4.exercise.leetcode.rank.year2023.october1;

/**
 * @date 2023/10/1 14:33
 * TODO
 */
public class Ex3 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Ex3().minSizeSubarray(nums, 5));
    }

    public int minSizeSubarray(int[] nums, int target) {
        int sum = 0, cnt = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target) {
            // 计算有几个完整的数组
            cnt = (target / sum) * n;
            target = target % sum;
        }
        int ans = Integer.MAX_VALUE;
        sum = nums[0];
        for (int leftPtr = 0, rightPtr = 0; rightPtr < 2 * n;) {
            if (sum >= target) {
                if (sum == target) {
                    ans = Math.min(ans, cnt + rightPtr - leftPtr + 1);
                }
                sum -= nums[(leftPtr++) % n];
            } else {
                sum += nums[(++rightPtr) % n];
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


}
