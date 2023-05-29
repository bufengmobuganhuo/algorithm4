package com.mengyu.algs4.interview.bytedance.mar30th;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/3/30 上午10:02
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        ex3.subsets(new int[]{1, 2, 3});
    }

    private List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            backtracking(nums, 0, new ArrayList<>(), i);
        }
        return res;
    }

    private void backtracking(int[] nums, int idx, List<Integer> track, int size) {
        if (track.size() == size) {
            List<Integer> tmp = new ArrayList<>(track);
            res.add(tmp);
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            track.add(nums[i]);
            backtracking(nums, i + 1, track, size);
            track.remove(track.size() - 1);
        }
    }
}
