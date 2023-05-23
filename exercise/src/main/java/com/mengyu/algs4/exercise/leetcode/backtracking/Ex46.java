package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/7/28 8:30 上午
 * TODO
 */
public class Ex46 {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        Ex46 ex46=new Ex46();
        ex46.permute(nums);
    }
    private List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        if (nums==null||nums.length==0){
            return new ArrayList<>();
        }
        ans=new ArrayList<>();
        backtracking(nums,new LinkedList<>());
        return ans;
    }

    private void backtracking(int[] nums, LinkedList<Integer> track){
        // 结束条件，所有数字均取完
        if (track.size()==nums.length){
            List<Integer> permuteList=new ArrayList<>(track);
            ans.add(permuteList);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 做选择,选择从未选择过的数字
            if (track.contains(nums[i])){
                continue;
            }
            track.offer(nums[i]);
            backtracking(nums,track);
            //撤销选择
            track.removeLast();
        }
    }
}
