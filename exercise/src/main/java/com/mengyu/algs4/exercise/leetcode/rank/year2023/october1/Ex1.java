package com.mengyu.algs4.exercise.leetcode.rank.year2023.october1;

/**
 * @date 2023/10/1 09:14
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        int[] nums = {1,10,3,4,19};
        System.out.println(new Ex1().maximumTripletValue(nums));
    }

    public long maximumTripletValue(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int numI = nums[i];
            for (int j = i + 1; j < n - 1; j++) {
                int numJ = nums[j];
                for (int k = j + 1; k < n; k++) {
                    int numK = nums[k];
                    ans = Math.max(ans, (long) (numI - numJ) * numK);
                }
            }
        }
        return ans;
    }
}
