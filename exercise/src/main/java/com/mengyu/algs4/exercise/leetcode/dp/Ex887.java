package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex887 {

    public static void main(String[] args) {
        System.out.println(new Ex887().superEggDrop(1, 2));
    }

    /**
     * dp[k][m]当有k个鸡蛋，能在m步中测出的最多楼层数
     * 当在x层扔鸡蛋时：
     * （1）如果鸡蛋碎了，则少了一个鸡蛋，并且少了一步，因此可以测出的楼层为：1 + dp[k-1][m-1](第x层测试过了)
     * （2）如果鸡蛋没碎，则鸡蛋没少，但是少了一步，因此可以测出的不碎楼层为：1 + dp[k][m-1](第x层测试过了)
     * https://leetcode.cn/problems/super-egg-drop/solutions/7459/ji-dan-diao-luo-xiang-jie-by-shellbye
     */
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for (int m = 1; m < n + 1; m++) {
            // 没有鸡蛋，测不出来
            dp[0][m] = 0;
            for (int K = 1; K < k + 1; K++) {
                dp[K][m] = dp[K][m - 1] + dp[K - 1][m - 1] + 1;
                // 找到了
                if (dp[K][m] >= n) {
                    return m;
                }
            }
        }
        // 没找到，最多会用n步
        return n;
    }
}
