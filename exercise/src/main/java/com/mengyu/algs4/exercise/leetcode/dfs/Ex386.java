package com.mengyu.algs4.exercise.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex386 {
    /**
     * n=21
     * 1, 10(优先向后添加0（*10），直到>n)，11(添加完0之后，+1)，12，...,19(不可以继续添加，19%10 = 9, 此时应该退一位（/10）)
     * 2, 20, 21(21 + 1 > n，退一位), 3, 4, 5, 6, 7, 8, 9
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ans.add(number);
            // 优先添加0
            if (number * 10 <= n) {
                number *= 10;
            } else {
                // 例如，对于2位数，到了19，或者n=21时，number=20
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return ans;
    }
}
