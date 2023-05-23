package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex448 {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        Ex448 ex448 = new Ex448();
        System.out.println(ex448.findDisappearedNumbers(nums));
    }

    /**
     * 1. 当遍历到nums[i]=x，则将nums[x-1]的值+n，表示数x被访问到了（在遍历的过程中需要%n, 因为后面的可能被加过n）
     * 2. 遍历完后再检查一次，当nums[i] < n，则说明对应位置的数i+1没有被访问过
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1) % n] += n;
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n){
                list.add(i + 1);
            }
        }
        return list;
    }
}
