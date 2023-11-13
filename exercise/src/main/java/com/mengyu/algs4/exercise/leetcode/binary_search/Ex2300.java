package com.mengyu.algs4.exercise.leetcode.binary_search;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2300 {

    public static void main(String[] args) {
        int[] spells = {};
        int[] potions = {100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000};
        System.out.println(Arrays.toString(new Ex2300().successfulPairs(spells, potions, 10000000000L)));
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length, n = potions.length;
        Arrays.sort(potions);
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = ceil(potions, spells[i], success);
            if (idx != -1) {
                ans[i] = n - idx;
            }
        }
        return ans;
    }

    private int ceil(int[] potions, long spell, long target) {
        int leftPtr = 0, rightPtr = potions.length - 1;
        int ceilIdx = -1;
        while (leftPtr <= rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            long multi = spell * (long) potions[midPtr];
            if (multi == target) {
                while (midPtr > 0 && multi == target) {
                    midPtr--;
                    multi = spell * (long) potions[midPtr];
                }
                if (midPtr == 0 && multi == target) {
                    return 0;
                }
                return midPtr + 1;
            } else if (multi < target) {
                leftPtr = midPtr + 1;
            } else {
                ceilIdx = midPtr;
                rightPtr = midPtr - 1;
            }
        }
        return ceilIdx;
    }
}
