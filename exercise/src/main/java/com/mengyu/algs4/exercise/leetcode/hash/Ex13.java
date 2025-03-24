package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex13 {

    public static void main(String[] args) {
        System.out.println(new Ex13().romanToInt("MCMXCIV"));
    }

    private Map<String, Integer> map;

    public Ex13() {
        this.map = new HashMap<>();
        this.map.put("I", 1);
        this.map.put("V", 5);
        this.map.put("X", 10);
        this.map.put("L", 50);
        this.map.put("C", 100);
        this.map.put("D", 500);
        this.map.put("M", 1000);
        this.map.put("IV", 4);
        this.map.put("IX", 9);
        this.map.put("XL", 40);
        this.map.put("XC", 90);
        this.map.put("CD", 400);
        this.map.put("CM", 900);
    }

    public int romanToInt(String s) {
        int ans = 0, n = s.length();
        for (int i = 0; i < n; ) {
            char chr = s.charAt(i);
            int num1 = 0;
            if (i < n - 1) {
                num1 = map.getOrDefault(s.substring(i, i + 2), 0);
            }
            int num2 = map.get(String.valueOf(chr));
            if (num1 != 0) {
                ans += num1;
                i += 2;
            } else {
                ans += num2;
                i++;
            }
        }
        return ans;
    }
}
