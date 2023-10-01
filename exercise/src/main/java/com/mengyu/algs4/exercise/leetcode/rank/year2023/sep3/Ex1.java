package com.mengyu.algs4.exercise.leetcode.rank.year2023.sep3;

/**
 * @date 2023/9/3 10:01
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        System.out.println(new Ex1().countSymmetricIntegers(1, 100));
    }

    public int countSymmetricIntegers(int low, int high) {
        int cnt = 0;
        for (int i = low; i < high + 1; i++) {
            if (ok(i)) {
                System.out.println(i);
                cnt++;
            }
        }
        return cnt;
    }

    private boolean ok(int num) {
        if (num <= 10) {
            return false;
        }
        int[] preSum = new int[6];
        int idx = 1;
        while (num != 0) {
            int digit = num % 10;
            preSum[idx] = preSum[idx - 1] + digit;
            num /= 10;
            idx++;
        }
        if ((idx - 1) % 2 != 0) {
            return false;
        }
        return preSum[idx / 2] == preSum[idx - 1] - preSum[idx / 2];
    }
}
