package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex338 {
    /**
     * 对于一个数x，令x = x & (x-1),可以让x的最低位变成0
     * 一直循环直到x=0，就可以知道x中1的个数，时间复杂度O(nlog(n))
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            res[i] = countOnes(i);
        }
        return res;
    }

    /**
     * 1. 对于一个数x(如13：1101), 寻找一个数y，令y <= x且y是2的整数次幂数(y=8:1000)，称为最高有效位，
     * 可知y的最高位是1，其他位都是0，令z = x - y(z=5: 0101)，则可知z <= x，并且x的1的个数比z的多1
     * 2. 如果x & (x-1)=0，x就是最高有效位
     */
    public int[] countBits2(int n) {
        int highBit = 0;
        int[] res = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
                res[i] = 1;
                continue;
            }
            res[i] = res[i - highBit] + 1;
        }
        return res;
    }

    /**
     * 好理解
     * 0：0（0）
     * 1：1（1）
     * 2：1（10）
     * 3：2（11）
     * 4：1（100）
     * 5：2（101）
     * 可以看出来，对于一个奇数，他是前一个数（偶数）+1；对于偶数，他是上一个偶数向左移动一位（比如8是4向左移动一位，所以1的个数不变）
     */
    public int[] countBits3(int n) {
        if (n == 0) {
            return new int[]{0};
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i/2];
            }else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp;
    }

    private int countOnes(int x) {
        int count = 0;
        while (x > 0) {
            x &= (x - 1);
            count++;
        }
        return count;
    }
}
