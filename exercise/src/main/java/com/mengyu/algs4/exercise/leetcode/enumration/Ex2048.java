package com.mengyu.algs4.exercise.leetcode.enumration;

/**
 * @author yu zhang
 */
public class Ex2048 {

    public static void main(String[] args) {
        System.out.println(new Ex2048().nextBeautifulNumber(1));
    }

    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < Integer.MAX_VALUE; i++) {
            int[] cnt = new int[10];
            int tmp = i;
            while (tmp != 0) {
                int digit = tmp % 10;
                tmp /= 10;
                cnt[digit]++;
            }
            boolean check = true;
            for (int j = 0; j < 10; j++) {
                if (cnt[j] != 0 && cnt[j] != j) {
                    check = false;
                    break;
                }
            }
            if (check) {
                return i;
            }
        }
        return -1;
    }
}
