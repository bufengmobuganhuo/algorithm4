package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;

/**
 * @author yu zhang
 */
public class Ex2336 {

    private final HashSet<Integer> missedSet;

    private int startNum;

    public Ex2336() {
        missedSet = new HashSet<>();
        startNum = 1;
    }

    public int popSmallest() {
        int res = startNum;
        startNum++;
        while (missedSet.contains(startNum)) {
            startNum++;
        }
        return res;
    }

    public void addBack(int num) {
        missedSet.remove(num);
        if (num < startNum) {
            for (int i = num + 1; i < startNum; i++) {
                missedSet.add(i);
            }
            startNum = num;
        }
    }
}
