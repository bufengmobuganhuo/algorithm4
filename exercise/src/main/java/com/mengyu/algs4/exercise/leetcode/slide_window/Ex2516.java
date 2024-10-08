package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex2516 {

    public static void main(String[] args) {
        System.out.println(new Ex2516().takeCharacters("acbcc", 1));
    }

    public int takeCharacters(String s, int k) {
        if (k <= 0) {
            return 0;
        }
        int n = s.length();
        int[] rightMap = new int[3];
        int leftPtr = 0, rightPtr = n - 1;
        for (; rightPtr >= 0; rightPtr--) {
            int idx = s.charAt(rightPtr) - 'a';
            rightMap[idx]++;
            if (rightMap[0] >= k && rightMap[1] >= k && rightMap[2] >= k) {
                break;
            }
        }
        if (rightPtr < 0) {
            return -1;
        }
        int ans = n - rightPtr;
        int[] leftMap = new int[3];
        while (leftPtr < n) {
            int idx = s.charAt(leftPtr) - 'a';
            leftMap[idx]++;
            if (leftMap[0] >= k && leftMap[1] >= k && leftMap[2] >= k) {
                ans = Math.min(ans, leftPtr + 1);
                break;
            }
            while (rightPtr < n) {
                int idx2 = s.charAt(rightPtr) - 'a';
                int tmp = rightMap[idx2];
                rightMap[idx2]--;
                int a = rightMap[0] + leftMap[0];
                int b = rightMap[1] + leftMap[1];
                int c = rightMap[2] + leftMap[2];
                if (a >= k && b >= k && c >= k) {
                    rightPtr++;
                    ans = Math.min(ans, leftPtr + 1 + n - rightPtr);
                } else {
                    rightMap[idx2] = tmp;
                    break;
                }
            }
            leftPtr++;
        }
        return ans;
    }
}
