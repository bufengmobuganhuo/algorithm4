package com.mengyu.algs4.exercise.leetcode.rank.year2023.december10;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2023/12/10 10:28
 * TODO
 */
public class Ex2 {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int[] var = variables[i];
            int mod1 = pow(var[0], var[1], 10);
            int mod2 = pow(mod1, var[2], var[3]);
            if (target == mod2) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int pow(int a, int b, int mod) {
        int ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= a;
            ans %= mod;
        }
        return ans;
    }
}
