package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author yu zhang
 */
public class Ex1488 {

    public static void main(String[] args) {
        int[] rains = {1,0,2,0,2,1};
        System.out.println(Arrays.toString(new Ex1488().avoidFlood(rains)));
    }

    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        TreeSet<Integer> st = new TreeSet<>();
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                st.add(i);
            } else {
                ans[i] = -1;
                if (mp.containsKey(rains[i])) {
                    Integer idx = st.ceiling(mp.get(rains[i]));
                    if (idx == null) {
                        return new int[0];
                    }
                    ans[idx] = rains[i];
                    st.remove(rains[i]);
                }
                mp.put(rains[i], i);
                ans[i] = -1;
            }
        }
        return ans;
    }
}
