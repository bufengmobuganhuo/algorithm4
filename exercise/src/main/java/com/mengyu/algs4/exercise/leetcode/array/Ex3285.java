package com.mengyu.algs4.exercise.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex3285 {
    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> ans = new ArrayList<>();
        int left = -1;
        for (int i = 0; i < height.length; i++) {
            if (left > threshold) {
                ans.add(i);
            }
            left = height[i];
        }
        return ans;
    }
}
