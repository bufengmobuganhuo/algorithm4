package leetcode.array;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1508 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(new Ex1508().rangeSum(nums, 4, 1, 10));
    }
    private static final int MOD = 1000000007;

    public int rangeSum(int[] nums, int n, int left, int right) {
        long[] preSum = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            preSum[i] = preSum[i-1] + nums[i - 1];
        }
        long[] subtractSum = new long[n * (n + 1) / 2];
        int idx= 0;
        for (int i = 0; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                subtractSum[idx++] = preSum[j] - preSum[i];
            }
        }
        Arrays.sort(subtractSum);
        long res = 0;
        for (int i = left - 1; i < right; i++) {
            res += subtractSum[i] % MOD;
        }
        return (int) (res % MOD);
    }
}
