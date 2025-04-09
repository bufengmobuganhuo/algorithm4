package com.mengyu.algs4.interview.fuguo;

/**
 * @date 2025/3/9 20:47
 * TODO
 */
public class Solution {
    /**
     * 计算到达台阶顶的方法数
     * @param n 台阶数
     * @return 到达台阶顶的方法数
     */
    public static int count(int n) {
        if (n <= 1) {
            return 1;
        }
        int cnt = count(n - 1);
        if (n >= 3) {
            cnt += count(n - 3);
        }
        return cnt;
    }
}
