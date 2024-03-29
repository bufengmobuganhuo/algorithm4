package com.mengyu.algs4.exercise.leetcode.math;

import java.util.Random;

/**
 * @author yu zhang
 */
public class Ex398 {
    private int[] nums;

    private static final Random random = new Random();

    public Ex398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int ans = -1, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == target) {
                cnt++;
                if (random.nextInt(cnt) == 0) {
                    /**
                     * cnt = 1时，RANDOM.nextInt(cnt)一定=0，所以是以1的概率保留
                     * cnt > 1时，RANDOM.nextInt(cnt)=0的概率为1/cnt，所以是以1/i的概率保留
                     */
                    ans = i;
                }
            }
        }
        return ans;
    }
}
