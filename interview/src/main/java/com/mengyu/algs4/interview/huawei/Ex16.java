package com.mengyu.algs4.interview.huawei;

/**
 * @author yuzhang
 * @date 2021/3/30 下午8:52
 * TODO
 */
public class Ex16 {
    public static void main(String[] args) {
        Ex16 ex16 = new Ex16();
        int[] nums = {3, 4, 2, 3};
        System.out.println(ex16.checkPossibility(nums));
    }

    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int count = 0;
        int idx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
                idx = i;
            }
            if (count >= 2) {
                return false;
            }
        }
        if (count == 1) {
            if (idx < nums.length - 1){
                nums[idx] = Math.min(nums[idx - 1], nums[idx + 1]);
                for (int i = idx + 1; i < nums.length; i++) {
                    if (nums[idx] < nums[idx - 1]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
