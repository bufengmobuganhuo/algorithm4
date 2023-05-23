package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex461 {
    public int hammingDistance(int x, int y) {
        int count = 0;
        while (x != 0 || y != 0){
            if ((x & 1) != (y & 1)){
                count++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return count;
    }
}
