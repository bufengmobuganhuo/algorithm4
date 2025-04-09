package com.mengyu.algs4.interview.xingyeshujin;

/**
 * @date 2025/2/25 12:28
 * TODO
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 2, 2, 1};
        System.out.println(new Solution().maxLength(nums));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 最长山脉的长度
     *
     * @param nums int整型一维数组 每个元素表示一座山的高度
     * @return int整型
     */
    public int maxLength(int[] nums) {
        // write code here
        int len = 0;
        int n = nums.length;

        boolean flag = true;

        for (int l = 0, r = 1; r < n; ) {
            if (nums[r] > nums[r - 1] && flag) {
                r++;
            } else if (nums[r] < nums[r - 1] && flag) {
                r++;
                flag = false;
            } else if (nums[r] < nums[r - 1] && !flag) {
                r++;
            } else if (nums[r] > nums[r - 1] && !flag) {
                len = Math.max(len, r - l);
                l = r - 1;
                flag = true;
            } else {
                l = r;
                r++;
            }
        }

        return len;
    }
}
