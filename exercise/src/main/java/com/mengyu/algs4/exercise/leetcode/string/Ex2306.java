package com.mengyu.algs4.exercise.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2306 {

    public static void main(String[] args) {
        String[] ideas = {"coffee", "donuts", "time", "toffee"};
        System.out.println(new Ex2306().distinctNames(ideas));
    }

    public long distinctNames(String[] ideas) {
        Map<Character, Set<String>> preMap = new HashMap<>();
        for (String idea : ideas) {
            preMap.computeIfAbsent(idea.charAt(0), key -> new HashSet<>()).add(idea.substring(1));
        }
        long ans = 0;
        for (Map.Entry<Character, Set<String>> entryA : preMap.entrySet()) {
            Set<String> setA = entryA.getValue();
            for (Map.Entry<Character, Set<String>> entryB : preMap.entrySet()) {
                Set<String> setB = entryB.getValue();
                if (entryA.getKey() == entryB.getKey()) {
                    continue;
                }
                int intersect = intersect(setA, setB);
                ans += (long) (setA.size() - intersect) * (setB.size() - intersect);
            }
        }
        return ans;
    }

    private int intersect(Set<String> setA, Set<String> setB) {
        int cnt = 0;
        for (String a : setA) {
            if (setB.contains(a)) {
                cnt++;
            }
        }
        return cnt;
    }
}
