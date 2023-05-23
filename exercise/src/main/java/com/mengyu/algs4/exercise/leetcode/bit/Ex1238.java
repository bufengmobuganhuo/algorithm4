package com.mengyu.algs4.exercise.leetcode.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1238 {
    public static void main(String[] args) {
        System.out.println(new Ex1238().circularPermutation(3, 2));
    }
    private boolean[] marked;

    public Ex1238() {
        marked = new boolean[(int) (pow(2, 16))];
    }

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans = new ArrayList<>();
        ans.add(start);
        int last = start ^ 1;
        int max = (int) (pow(2, n) - 1);
        Arrays.fill(marked, 0, max, false);
        for (int i = 0; i < max - 1; i++) {
            int x = 1, lastNum = ans.get(ans.size() - 1);
            int num = lastNum ^ x;
            while (marked[num] || last == num) {
                x = x<< 1;
                num = lastNum ^ x;
            }
            marked[num] = true;
            ans.add(num);
        }
        ans.add(last);
        return ans;
    }

    private int pow(int a, int b) {
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
            }
            a *= a;
            b = b >> 1;
        }
        return res;
    }
}
