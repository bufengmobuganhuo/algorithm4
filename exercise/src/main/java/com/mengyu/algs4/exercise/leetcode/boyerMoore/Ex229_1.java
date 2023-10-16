package com.mengyu.algs4.exercise.leetcode.boyerMoore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex229_1 {
    public List<Integer> majorityElement(int[] nums) {
        int[] candidate1 = new int[2];
        int[] candidate2 = new int[2];

        for (int num : nums) {
            if (candidate1[0] == num) {
                candidate1[1]++;
            } else if (candidate2[0] == num) {
                candidate2[1]++;
            } else if (candidate1[1] == 0) {
                candidate1[0] = num;
                candidate1[1] = 1;
            } else if (candidate2[1] == 0) {
                candidate2[0] = num;
                candidate2[1] = 1;
            } else {
                candidate1[1]--;
                candidate2[1]--;
            }
        }

        candidate1[1] = 0;
        candidate2[1] = 0;
        for (int num : nums) {
            if (candidate1[0] == num) {
                candidate1[1]++;
            } else if (candidate2[0] == num) {
                candidate2[1]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (candidate1[1] > nums.length / 3) {
            ans.add(candidate1[0]);
        }
        if (candidate2[1] > nums.length / 3) {
            ans.add(candidate2[0]);
        }
        return ans;
    }
}
