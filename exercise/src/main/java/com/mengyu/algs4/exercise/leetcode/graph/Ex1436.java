package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1436 {
    public String destCity(List<List<String>> paths) {
        Map<String, Integer> degrees = new HashMap<>();
        for (List<String> path : paths) {
            degrees.put(path.get(0), degrees.getOrDefault(path.get(0), 0) + 1);
            if (!degrees.containsKey(path.get(1))) {
                degrees.put(path.get(1), 0);
            }
        }
        for (String key : degrees.keySet()) {
            if (degrees.get(key) == 0) {
                return key;
            }
        }
        return null;
    }
}
