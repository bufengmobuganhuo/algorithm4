package com.mengyu.algs4.exercise.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1276 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int y = 4 * cheeseSlices - tomatoSlices;
        if (y < 0 || y % 2 != 0) {
            return new ArrayList<>();
        } else if (cheeseSlices - y < 0) {
            return new ArrayList<>();
        }
        return Arrays.asList(cheeseSlices - y, y);

    }
}
