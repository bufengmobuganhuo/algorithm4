package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex2972 {
    /**
     * 子数组是一段连续的区间，那删除子数组后，会在左右两端各留下一段连续区间，让这部分区间成为一个递增序列，类似下图
     * [(左端递增区间)...(右端递增区间)]
     * 1. 先统计左端的连续区间，使用一个指针l，从左开始遍历，找到最长的一段连续递增区间。
     * 这其中会有一个特殊情况：l到达最右端，即数组本身就是递增的，这个时候可以删除的子数组数=n*(n+1)/2
     * 找到左端的最长递增区间后，那如果只保留左端连续区间的全部或部分，剩下的全部删除的话，则可以删除l + 1 + 1个子数组（l+1是左端连续区间的长度）
     * 2. 再统计右端的连续区间，同样使用一个指针r，从右开始遍历
     * 因为右端的区间要和左端的区间组成一个完整的递增序列，则对于遍历过程中每一个r，需要保证2点：
     * (1) 从r -> n - 1 这一段是递增的
     * (2) [0, l]和[r, n - 1]组成的子序列也是递增的，此时可以删除的子数组个数=l + 1 + 1（l+1是左端连续区间的长度，再考虑全部删除左端递增区间的情况）
     *
     * 由此两部分得到答案
     * @param nums
     * @return
     */
    public long incremovableSubarrayCount(int[] nums) {
        long ans = 0, n = nums.length;
        int l = 0;
        while (l < n - 1) {
            if (nums[l] >= nums[l + 1]) {
                break;
            }
            l++;
        }
        if (l == n - 1) {
            return n * (n + 1) / 2;
        }
        ans += l + 2;

        for (int r = (int) (n - 1); r >= 0; r--) {
            if (r < n - 1 && nums[r] >= nums[r + 1]) {
                break;
            }

            while (l >= 0 && nums[l] >= nums[r]) {
                l--;
            }
            ans += l + 2;
        }
        return ans;
    }
}
