package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex699 {
    public static void main(String[] args) {
        int[][] positions = {
                {1, 2},
                {2, 3},
                {6, 1},
        };
        System.out.println(new Ex699().fallingSquares(positions));
    }

    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left1 = positions[i][0], right1 = positions[i][0] + positions[i][1] - 1;
            int height = positions[i][1];
            // 如果和前面的有重叠，则更新高度
            for (int j = 0; j < i; j++) {
                int left2 = positions[j][0], right2 = positions[j][0] + positions[j][1] - 1;
                if (right1 >= left2 && right2 >= left1) {
                    height = Math.max(height, heights.get(j) + positions[i][1]);
                }
            }
            heights.add(height);
        }
        // 求的是第i块正方形落稳后，目前已有的最大堆叠高度，但是上面的只计算了当前能到达的最大高度，所以需要和前面比较
        for (int i = 1; i < n; i++) {
            heights.set(i, Math.max(heights.get(i), heights.get(i - 1)));
        }
        return heights;
    }
}
