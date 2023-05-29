package com.mengyu.algs4.interview.kuaishou.apr5th;

/**
 * @author yuzhang
 * @date 2021/4/5 上午11:37
 * TODO
 */
public class Ex3 {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
