package leetcode.dp;

import leetcode.array.Ex27;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/9/1 8:41 下午
 * 1. 提取状态：F(i):组成i的完全平方数的个数
 * 2. 状态转移方程：F(n)=F(i)+F(n-i),其中i取值范围为[1,n)
 */
public class Ex279 {
    public static void main(String[] args) {
        Ex279 ex279 = new Ex279();
        System.out.println(ex279.numSquares(12));
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        // 提前算好完全平方数
        for (int i = 1; i < n; i++) {
            int multi = (int) Math.pow(i, 2);
            if (multi > n) {
                break;
            }
            dp[multi] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            if (dp[i] == 1) {
                continue;
            }
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[j] + dp[i - j], dp[i]);
            }
        }
        return dp[n];
    }
}
