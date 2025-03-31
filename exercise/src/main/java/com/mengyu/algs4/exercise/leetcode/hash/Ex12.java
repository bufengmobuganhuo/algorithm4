package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.TreeMap;

/**
 * @author yu zhang
 */
public class Ex12 {

    public static void main(String[] args) {
        System.out.println(new Ex12().intToRoman(3749));
    }

    private final TreeMap<Integer, String> map;

    public Ex12() {
        map = new TreeMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");

        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");
    }

    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            int divided = map.floorKey(num);
            int cnt = num / divided;
            if ((divided == 1 || divided == 10 || divided == 100 || divided == 1000)) {
                for (int i = 0; i < Math.min(3, cnt); i++) {
                    res.append(map.get(divided));
                    num -= divided;
                }
                if (cnt > 3) {
                    int next = map.floorKey(divided);
                    res.append(map.get(next));
                    num -= next;
                }
            } else {
                for (int i = 0; i < cnt; i++) {
                    res.append(map.get(divided));
                    num -= divided;
                }
            }
        }
        return res.toString();
    }
}
