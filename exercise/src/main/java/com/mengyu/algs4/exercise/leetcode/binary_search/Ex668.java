package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex668 {
    public static void main(String[] args) {
        System.out.println(new Ex668().findKthNumber(3, 3, 5));
    }

    /**
     * 对于一个数字x
     * 1. 对于乘法表的第i行(i从1开始取)，所有数字都是i的倍数，因此不超过x的个数为min(x/i, n)
     * 因此可以遍历i(1...m)，计算上述的和=sum(min(x/i, n)) ，其中i=1....m
     * 2. 同时因为i <= x/n时，n <= x/i，所以上式可以简化为：x/n * n + sum(x/i) ，其中i=x/n + ...m
     */
    public int findKthNumber2(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int x = left + (right - left) / 2;
            int count = x / n * n;
            for (int i = x / n + 1; i <= m; i++) {
                count += x / i;
            }
            if (count >= k) {
                right = x;
            } else {
                left = x + 1;
            }
        }
        return left;
    }

    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(m, n, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int m, int n, int mid, int k) {
        int i = m - 1, j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            int num = (i + 1) * (j + 1);
            if (num <= mid) {
                count += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return count >= k;
    }
}
