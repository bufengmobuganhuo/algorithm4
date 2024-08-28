package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex3137 {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        Map<String, Integer> cntMap = new HashMap<>();
        int leftPtr = 0, rightPtr = k - 1, n = word.length();
        int ans = Integer.MAX_VALUE;
        while (rightPtr < n) {
            if (leftPtr % k == 0) {
                String str = word.substring(leftPtr, rightPtr + 1);
                int cnt = cntMap.getOrDefault(str, 0);
                cnt++;
                cntMap.put(str, cnt);
                ans = Math.min(ans, (n - k * cnt) / k);
            }
            leftPtr++;
            rightPtr++;
        }
        return ans;
    }
}
