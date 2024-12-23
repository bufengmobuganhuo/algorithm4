package com.mengyu.algs4.exercise.leetcode.sort;

/**
 * @author yu zhang
 */
public class Ex1338 {

    public static void main(String[] args) {
        int[] arr = {1, 9};
        System.out.println(new Ex1338().minSetSize(arr));
    }

    public int minSetSize(int[] arr) {
        int[] cntMap = new int[100001];
        for (int num : arr) {
            cntMap[num]++;
        }
        int n = arr.length;
        int[] freqCnt = new int[n + 1];
        for (int cnt : cntMap) {
            if (cnt > 0) {
                freqCnt[cnt]++;
            }
        }
        int target = n / 2, cnt = 0, sum = 0;
        for (int i = n; i > 0; i--) {
            if (target - sum > freqCnt[i] * i) {
                sum += freqCnt[i] * i;
                cnt += freqCnt[i];
            } else {
                return cnt + ((target - sum) + i - 1) / i;
            }
        }
        return n;
    }
}
