package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1604_1 {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            int hour = (keyTime[i].charAt(0) - '0') * 10 + keyTime[i].charAt(1) - '0';
            int minute = (keyTime[i].charAt(3) - '0') * 10 + keyTime[i].charAt(4) - '0';
            map.computeIfAbsent(keyName[i], key -> new ArrayList<>()).add(hour * 60 + minute);
        }
        List<String> ans = new ArrayList<>();
        for (String name : map.keySet()) {
            List<Integer> time = map.get(name);
            Collections.sort(time);
            for (int i = 2; i < time.size(); i++) {
                int interval = time.get(i) - time.get(i - 2);
                if (interval <= 60) {
                    ans.add(name);
                    break;
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
