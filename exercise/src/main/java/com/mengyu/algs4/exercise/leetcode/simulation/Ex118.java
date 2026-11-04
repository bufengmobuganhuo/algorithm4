package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuzhang
 * @date 2026/11/4 23:14
 */
public class Ex118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> line = new ArrayList<>();
            line.add(1);
            for (int j = 1; j < i; j++) {
                List<Integer> lastLine = result.get(i - 1);
                line.add(lastLine.get(j - 1) + lastLine.get(j));
            }
            if (i != 0) {
                line.add(1);
            }
            result.add(line);
        }
        return result;
    }
}
