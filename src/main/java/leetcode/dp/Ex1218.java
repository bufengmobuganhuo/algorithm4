package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/11/6 10:18 上午
 * TODO
 */
public class Ex1218 {
    /**
     * 1. dp[i]:以arr[i]结尾的等差数列最长的长度
     * 2. 对于一个j < i，且arr[i] - arr[j] = d, 则dp[i] = dp[j] + 1
     * 3. 由于是从左往右找，可以令v = arr[i]，dp[v]：以v结尾的等差数列长度，则dp[v] = dp[v - d] + 1
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        int res = 1;
        Map<Integer, Integer> dp = new HashMap<>();
        for (int num : arr) {
            int lastLen = dp.getOrDefault(num - difference, 0);
            dp.put(num, lastLen + 1);
            res = Math.max(res, lastLen + 1);
        }
        return res;
    }
}
