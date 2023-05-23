package com.mengyu.algs4.exercise.leetcode.rank.year2022.may29;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/5/29 10:24
 * TODO
 */
public class Ex1 {
    public int rearrangeCharacters(String s, String target) {
        Map<Character, Integer> map = new HashMap<>();
        for (char chr : s.toCharArray()) {
            map.put(chr, map.getOrDefault(chr, 0) + 1);
        }
        int ans = 0;
        while (true) {
            boolean stop = false;
            for (char chr : target.toCharArray()) {
                int count = map.getOrDefault(chr, 0);
                if (count <= 0) {
                    map.remove(chr);
                    stop = true;
                    break;
                } else {
                    map.put(chr, count - 1);
                }
            }
            if (stop) {
                break;
            }
            ans++;
        }

        return ans;
    }
}
