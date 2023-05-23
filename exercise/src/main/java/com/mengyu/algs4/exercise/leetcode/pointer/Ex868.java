package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex868 {
    public static void main(String[] args) {
        System.out.println(new Ex868().binaryGap(22));
    }
    public int binaryGap(int n) {
        int ans = 0, last1 = -1, idx = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                if (last1 != -1) {
                    ans = Math.max(ans, idx - last1);
                }
                last1 = idx;
            }
            n >>= 1;
            idx++;
        }
        return ans;
    }
}
