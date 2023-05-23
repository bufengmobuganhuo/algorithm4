package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1124 {
    /**
     * 1. 使用1表示超过8小时，-1表示不超过8小时，并且维护一个前缀和数组s，其中s[0]=0
     * 2. 当遇到s[i] > 0 时，表示长度为i+1的连续子数组是一个符合条件的答案
     * 3. 当遇到s[i] <= 0 时, 需要从前面找到一个最先出现的j，使得s[i] - s[j] = 1，
     * 因为前缀和要么是+1，要么是-1，所以出现的只能是差1
     * 那么符合条件的答案=i - j
     */
    public int longestWPI(int[] hours) {
        int s = 0, len = hours.length, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            s += (hours[i] > 8 ? 1 : -1);
            if (s > 0) {
                ans = Math.max(ans, i + 1);
            } else {
                if (map.containsKey(s - 1)) {
                    ans = Math.max(ans, i - map.get(s - 1));
                }
            }
            // 存储最先出现的
            if (!map.containsKey(s)) {
                map.put(s, i);
            }
        }
        return ans;
    }
}
