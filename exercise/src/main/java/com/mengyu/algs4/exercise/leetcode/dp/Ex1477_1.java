package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1477_1 {

    public static void main(String[] args) {
        int[] arr = {2, 2, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(new Ex1477_1().minSumOfLengths(arr, 20));
    }

    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int defaultLen = 100001;
        int[] dp = new int[n];
        dp[0] = arr[0] == target ? 1 : defaultLen;
        int sum = arr[0];
        int l = 0, r = 1;
        int ans = defaultLen;
        while (l < n) {
            while (r < n && sum < target) {
                sum += arr[r];
                dp[r] = sum != target ? dp[r - 1] : Math.min(dp[r - 1], r - l + 1);
                r++;
            }
            if (sum == target) {
                ans = Math.min(ans, l > 0 ? dp[l - 1] + r - l : defaultLen);
                if (r < n) {
                    dp[r - 1] = Math.min(r > 1 ? dp[r - 2] : defaultLen, r - l);
                }
            }
            sum -= arr[l];
            l++;
        }
        return ans >= defaultLen ? -1 : ans;
    }
}
