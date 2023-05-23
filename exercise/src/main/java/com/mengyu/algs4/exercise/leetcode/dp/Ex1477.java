package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2022/5/3 11:18
 * TODO
 */
public class Ex1477 {
    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 3};
        System.out.println(new Ex1477().minSumOfLengths(arr, 3));
    }
    /**
     * 1. dp[i]：表示arr[0...i]中子数组和=target的最小长度
     * 2. 使用两个指针，可以找到子数组和=target的数组
     * 3. 关于dp[i]的取值：
     * dp[rightPtr] = dp[rightPtr-1], arr[leftPtr...rightPtr]的和!=target
     * dp[rightPtr] = min{dp[rightPtr-1], rightPtr-leftPtr+1}, arr[leftPtr...rightPtr]的和=target
     * 4. 最短的两个不重叠子数组长度和={dp[leftPtr-1] + rightPtr-leftPtr+1}, 如果arr[leftPtr...rightPtr]的和=target的话
     */
    public int minSumOfLengths(int[] arr, int target) {
        int leftPtr = 0, rightPtr = 0;
        int[] dp = new int[arr.length];
        // 表示非法长度，因为arr.length <= 10^5
        int defaultLen = 100001;
        dp[0] = arr[0] == target ? 1 : defaultLen;
        int sum = arr[0], minLen = defaultLen;
        while (leftPtr < arr.length) {
            while (sum < target) {
                rightPtr++;
                if (rightPtr >= arr.length) {
                    break;
                }
                sum += arr[rightPtr];
                dp[rightPtr] = Math.min(dp[rightPtr - 1], sum == target ?
                        rightPtr - leftPtr + 1 : defaultLen);
            }
            if (sum == target) {
                // 至少存在两个和=target的子数组
                if (leftPtr != 0 && dp[leftPtr - 1] != defaultLen) {
                    minLen = Math.min(minLen, dp[leftPtr - 1] + rightPtr - leftPtr + 1);
                }
                // =target导致跳出循环，无法更新dp
                dp[rightPtr] = Math.min(rightPtr > 0 ? dp[rightPtr - 1] : defaultLen, rightPtr - leftPtr + 1);
            }
            sum -= arr[leftPtr];
            leftPtr++;
        }
        return minLen == defaultLen ? -1 : minLen;
    }
}
