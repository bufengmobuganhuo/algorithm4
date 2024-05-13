package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex2105 {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int leftPtr = 0, rightPtr = n - 1;
        int aCurCap = capacityA, bCurCap = capacityB;
        int cnt = 0;
        while (leftPtr < rightPtr) {
            if (aCurCap < plants[leftPtr]) {
                aCurCap = capacityA;
                cnt++;
            }
            aCurCap -= plants[leftPtr];
            if (bCurCap < plants[rightPtr]) {
                bCurCap = capacityB;
                cnt++;
            }
            bCurCap -= plants[rightPtr];
            leftPtr++;
            rightPtr--;
        }
        if (leftPtr == rightPtr) {
            int curCap = Math.max(aCurCap, bCurCap);
            if (curCap < plants[leftPtr]) {
                cnt++;
            }
        }
        return cnt;
    }
}
