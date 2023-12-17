package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            if ((i - 1 < 0 || flowerbed[i - 1] == 0) && (i + 1 >= flowerbed.length || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                cnt++;
                if (cnt >= n) {
                    return true;
                }
            }
        }
        return cnt == n;
    }
}
