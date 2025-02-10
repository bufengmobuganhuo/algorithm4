package com.mengyu.algs4.exercise.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex78 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Ex78().subsets2(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        for (int mask = 0; mask < (1 << n); mask++) {
            t.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    private List<List<Integer>> ans;

    public List<List<Integer>> subsets2(int[] nums) {
        ans = new ArrayList<>();
        for (int k = 0; k <= nums.length; k++) {
            backtracking(0, k, nums, new ArrayList<>());
        }
        return ans;
    }

    private void backtracking(int start, int k, int[] nums, List<Integer> tmp) {
        if (k == 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtracking(i + 1, k - 1, nums, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
