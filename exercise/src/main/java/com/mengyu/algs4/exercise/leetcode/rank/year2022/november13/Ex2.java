package com.mengyu.algs4.exercise.leetcode.rank.year2022.november13;

/**
 * @author yuzhang
 * @date 2022/11/13 10:20
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 6, 2, 7, 1};
        System.out.println(new Ex2().subarrayLCM(nums, 6));
    }

    public int subarrayLCM(int[] nums, int k) {
        int count = 0, len = nums.length;
        // nums[j...i]：对于这个范围内的元素，单个元素和k组成的最小公倍数=k
        int j = -1;
        for (int i = 0; i < len; i++) {
            if (i + 1 < len && lcm(nums[i], k) == k && lcm(nums[i + 1], k) == k) {
                // 如果nums[i]和nums[i+1]组成的最小公倍数=k，
                // 那任何从nums[j+1...i]中的一个元素作为起点，nums[i+1]作为终点的子数组都满足条件
                // 则子数组个数=i - j;
                if (lcm(nums[i], nums[i + 1]) == k) {
                    count += (i - j);
                }
            } else {
                j = i;
            }
            // 计算单个元素
            if (nums[i] == k) {
                count++;
            }
        }
        return count;
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
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
