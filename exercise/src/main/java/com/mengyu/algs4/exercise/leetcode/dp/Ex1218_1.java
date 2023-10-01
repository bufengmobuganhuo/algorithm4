package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1218_1 {

    public static void main(String[] args) {
        int[] arr = {1,5,7,8,5,3,4,2,1};
        System.out.println(new Ex1218_1().longestSubsequence(arr, -2));
    }

    public int longestSubsequence(int[] arr, int difference) {
        int m = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 1);
        int ans = 1;
        for (int i = 1; i < m; i++) {
            int cnt = map.getOrDefault(arr[i] - difference, 0) + 1;
            map.put(arr[i], cnt);
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
