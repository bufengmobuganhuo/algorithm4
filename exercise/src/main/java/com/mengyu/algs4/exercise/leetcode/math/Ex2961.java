package com.mengyu.algs4.exercise.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2961 {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int a = variables[i][0], b = variables[i][1], c = variables[i][2], m = variables[i][3];
            long ab = pow(a, b, 10);
            long abc = pow(ab, c, m);
            if (abc == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    private long pow(long a, long b, long mod) {
        if (b == 0) {
            return 1;
        }
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
                res %= mod;
            }
            a *= a;
            a %= mod;
            b >>= 1;
        }
        return res;
    }
}
