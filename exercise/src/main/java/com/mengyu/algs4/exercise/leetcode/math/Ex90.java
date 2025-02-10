package com.mengyu.algs4.exercise.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex90 {
    private List<List<Integer>> ans;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length + 1; k++) {
            backtracking(0, k , 0, nums, new ArrayList<>());
        }
        return ans;
    }

    private void backtracking(int start, int k, int mask, int[] nums, List<Integer> tmp) {
        if (k == 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 如果遍历到i时，没选过i-1，意味着是从2个相同的数中选一个，则选谁都一样。
            if (i > 0 && nums[i] == nums[i - 1] && (mask & (1 << (i - 1))) == 0) {
                continue;
            }
            tmp.add(nums[i]);
            mask = mask | (1 << i);
            backtracking(i + 1, k - 1, mask, nums, tmp);
            mask = mask ^ (1 << i);
            tmp.remove(tmp.size() - 1);
        }
    }
}
