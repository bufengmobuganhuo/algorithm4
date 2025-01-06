package com.mengyu.algs4.exercise.leetcode.design;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author yu zhang
 */
public class Ex729 {
    private final TreeSet<int[]> treeSet;

    public Ex729() {
        treeSet = new TreeSet<>(Comparator.comparingInt(o -> o[0]));
    }

    public boolean book(int startTime, int endTime) {
        int[] range = {startTime, endTime};
        int[] floor = treeSet.floor(range);
        if (floor != null) {
            if (floor[0] == startTime || floor[1] > startTime) {
                return false;
            }
        }
        int[] ceil = treeSet.ceiling(range);
        if (ceil != null) {
            if (ceil[0] == startTime || ceil[0] < endTime) {
                return false;
            }
        }
        treeSet.add(range);
        return true;
    }
}
