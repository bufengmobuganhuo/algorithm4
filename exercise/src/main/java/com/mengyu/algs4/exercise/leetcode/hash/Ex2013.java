package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2013 {
    public static void main(String[] args) {
        Ex2013 ex2013 = new Ex2013();
        ex2013.add(new int[]{5, 10});
        ex2013.add(new int[]{10, 5});
        ex2013.add(new int[]{10, 10});
        System.out.println(ex2013.count(new int[]{5, 5}));
        ex2013.add(new int[]{3, 0});
        ex2013.add(new int[]{8, 0});
        ex2013.add(new int[]{8, 5});
        System.out.println(ex2013.count(new int[]{3, 5}));
        ex2013.add(new int[]{9, 0});
        ex2013.add(new int[]{9, 8});
        ex2013.add(new int[]{1, 8});
        System.out.println(ex2013.count(new int[]{1, 0}));
        ex2013.add(new int[]{0, 0});
        ex2013.add(new int[]{8, 0});
        ex2013.add(new int[]{8, 8});
        System.out.println(ex2013.count(new int[]{0, 8}));
    }

    /**
     * <y, <x, 坐标为(x,y)的个数>>
     */
    private HashMap<Integer, Map<Integer, Integer>> cntMap;

    public Ex2013() {
        cntMap = new HashMap<>();
    }

    public void add(int[] point) {
        Map<Integer, Integer> xMap = cntMap.computeIfAbsent(point[1], key -> new HashMap<>());
        xMap.put(point[0], xMap.getOrDefault(point[0], 0) + 1);
    }

    public int count(int[] point) {
        int x = point[0], y = point[1];
        Map<Integer, Integer> xMap = cntMap.get(y);
        if (xMap == null) {
            return 0;
        }
        int count = 0;
        // 和point相同纵坐标的点
        for (Map.Entry<Integer, Integer> entry: xMap.entrySet()) {
            int xAxis = entry.getKey();
            if (xAxis == x) {
                continue;
            }
            // 长度
            int distance = x - xAxis;
            // 向上找
            count += entry.getValue() // (xAxis, y)
                    // (x, y+d)
                    * cntMap.getOrDefault(y + distance, new HashMap<>()).getOrDefault(x, 0)
                    // (xAxis, y+d)
                    * cntMap.getOrDefault(y + distance, new HashMap<>()).getOrDefault(xAxis, 0);
            count += entry.getValue() // (xAxis, y)
                    // (x, y - d)
                    * cntMap.getOrDefault(y - distance, new HashMap<>()).getOrDefault(x, 0)
                    // (xAxis, y-d)
                    * cntMap.getOrDefault(y - distance, new HashMap<>()).getOrDefault(xAxis, 0);
        }
        return count;
    }
}
