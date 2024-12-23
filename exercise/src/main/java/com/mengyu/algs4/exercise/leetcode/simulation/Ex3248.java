package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.List;

/**
 * @author yu zhang
 */
public class Ex3248 {
    public int finalPositionOfSnake(int n, List<String> commands) {
        int i = 0, j = 0;
        for (String command : commands) {
            if (command.equals("RIGHT")) {
                j++;
            } else if (command.equals("LEFT")) {
                j--;
            } else if (command.equals("UP")) {
                i--;
            } else {
                i++;
            }
        }
        return i * n + j;
    }
}
