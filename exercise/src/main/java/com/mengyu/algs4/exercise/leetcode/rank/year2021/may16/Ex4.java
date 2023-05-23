package com.mengyu.algs4.exercise.leetcode.rank.year2021.may16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/5/15 下午11:22
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        int[] nums = {7,7,7,7,7,7,7};
        Ex4 ex4 = new Ex4();
        System.out.println(ex4.sumOfFlooredPairs(nums));
    }

    private int res;
    private final int mod = 1000000007;

    public int sumOfFlooredPairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        int count = 1;
        int dupNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == dupNum) {
                count++;
                nums[i - 1] = 0;
                nums[i] = 0;
            } else if (count != 1) {
                res += (count * count) % mod;
                for (int num : nums) {
                    if (num != dupNum && num != 0) {
                        res += (count * Math.floorDiv(num, dupNum)) % mod;
                        res += (count * Math.floorDiv(dupNum, num)) % mod;
                    }
                }
                count = 1;
                dupNum = nums[i];
            } else {
                list.add(dupNum);
                dupNum = nums[i];
            }
        }
        if (count != 1) {
            res += (count * count) % mod;
            for (int num : nums) {
                if (num != dupNum && num != 0) {
                    res += (count * Math.floorDiv(num, dupNum)) % mod;
                    res += (count * Math.floorDiv(dupNum, num)) % mod;
                }
            }
        }
        if (nums[nums.length-1]!=0){
            list.add(nums[nums.length - 1]);
        }
        backtracking(list, new ArrayList<>());
        return res;
    }

    private void backtracking(List<Integer> nums, List<Integer> track) {
        if (nums.size() <= 1) {
            res+=nums.size();
            return;
        }
        if (track.size() == 2) {
            if (track.get(1) != 0) {
                res += (Math.floorDiv(track.get(0), track.get(1)) % mod);
            }
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            track.add(nums.get(i));
            backtracking(nums, track);
            track.remove(track.size() - 1);
        }
    }
}
