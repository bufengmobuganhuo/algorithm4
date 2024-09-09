package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2860 {

    public static void main(String[] args) {
        System.out.println(new Ex2860().countWays(Arrays.asList(6, 0, 3, 3, 6, 7, 2, 7)));
    }

    public int countWays(List<Integer> nums) {
        nums.sort(Comparator.comparingInt(o -> o));
        int cnt = 0, n = nums.size();
        if (nums.get(0) > 0) {
            cnt++;
        }
        for (int i = 0; i < n; i++) {
            int num = nums.get(i), nextNum = i == n - 1 ? Integer.MAX_VALUE : nums.get(i + 1);
            int pickedNum = i + 1;
            if (pickedNum > num && nextNum > pickedNum) {
                cnt++;
            }
        }
        return cnt;
    }
}
