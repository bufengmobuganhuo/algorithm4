package com.mengyu.algs4.exercise.leetcode.rank.year2022.september18;

/**
 * @author yu zhang
 */
public class Ex1 {
    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        }
        return n * 2;
    }
}
