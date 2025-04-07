package com.mengyu.algs4.exercise.leetcode.pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex15 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new Ex15().threeSum(nums));
    }


    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; ) {
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                i++;
                continue;
            }
            // 如果目前使用最大的两个数+nums[i]<0，则说明说用nums[i]无法组成0
            if (nums[i] + nums[len - 2] + nums[len - 1] < 0) {
                i++;
                continue;
            }
            int num = nums[i];
            int target = -num;
            for (int j = i + 1; j < nums.length - 1; ) {
                if (nums[j] + nums[j + 1] > target) {
                    j++;
                    continue;
                }
                int targetIdx = search(nums, j + 1, target - nums[j]);
                if (targetIdx != -1) {
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    list.add(nums[j]);
                    list.add(nums[targetIdx]);
                    ans.add(list);
                }
                j++;
                while (j < nums.length && nums[j] == nums[j - 1]) {
                    j++;
                }
            }
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return ans;
    }

    private List<List<Integer>> twoSum(int[] nums, int startIdx, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = startIdx; i < nums.length; ) {
            int targetIdx = search(nums, i + 1, target - nums[i]);
            if (targetIdx != -1) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[targetIdx]);
                ans.add(list);
            }
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return ans;
    }

    private int search(int[] nums, int startIdx, int target) {
        int l = startIdx, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}
