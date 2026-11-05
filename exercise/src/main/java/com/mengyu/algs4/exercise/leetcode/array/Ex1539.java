package com.mengyu.algs4.exercise.leetcode.array;

/**
 *
 * @author yuzhang
 * @date 2026/11/5 23:10
 */
public class Ex1539 {

    public static void main(String[] args) {
        int[] arr = {2,3,4,7,11};
        System.out.println(new Ex1539().findKthPositive(arr, 5));
    }

    public int findKthPositive(int[] arr, int k) {
        if (arr[0] > k) {
            return k;
        }
        int missingCnt = arr[0] - 1;
        for (int i = 1; i < arr.length; i++) {
            int distance = arr[i] - arr[i - 1] - 1;
            if (missingCnt + distance >= k) {
                return arr[i - 1] + k - missingCnt;
            }
            missingCnt += distance;
        }
        return arr[arr.length - 1] + k - missingCnt;
    }
}
