package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2748 {

    public static void main(String[] args) {
        System.out.println(new Ex2748().countBeautifulPairs(new int[]{11, 21, 12}));
    }

    public int countBeautifulPairs(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = String.valueOf(nums[i]).charAt(0) - '0';
                String str = String.valueOf(nums[j]);
                int b = str.charAt(str.length() - 1) - '0';
                if (gcd(a, b) == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        int mod = 0;
        do {
            mod = a % b;
            a = b;
            b = mod;
        } while (b != 0);
        return a;
    }
}
