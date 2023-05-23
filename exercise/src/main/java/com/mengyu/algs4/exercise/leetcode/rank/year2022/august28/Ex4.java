package com.mengyu.algs4.exercise.leetcode.rank.year2022.august28;

import java.util.*;

/**
 * @author yuzhang
 * @date 2022/8/28 11:26
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        int[][] rowConditions = {{1, 2}, {2, 3}};
        int[][] colConditions = {{2, 1}};
        System.out.println(Arrays.deepToString(new Ex4().buildMatrix(3, rowConditions, colConditions)));
    }

    private boolean[] marked;

    private boolean[] onStack;

    private boolean hasCycle;

    private Stack<Integer> reversePostStack;

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] colMapped = mapped(k, colConditions);
        if (colMapped == null) {
            return new int[0][];
        }
        int[] rowMapped = mapped(k, rowConditions);
        if (rowMapped == null) {
            return new int[0][];
        }
        int[][] matrix = new int[k][k];
        for (int i = 1; i < k + 1; i++) {
            matrix[rowMapped[i]][colMapped[i]] = i;
        }
        return matrix;
    }

    private int[] mapped(int k, int[][] conditions) {
        marked = new boolean[k + 1];
        onStack = new boolean[k + 1];
        hasCycle = false;
        reversePostStack = new Stack<>();
        /**
         * 构造有向图，构造规则如下：
         * 令colConditions[0] < colConditions[1]，即越小的数字越靠左
         * 令rowConditions[0] < rowConditions[1]，即越小的数字越靠上
         *
         * 如果数字x < 数字y，则规定有一个从节点x到节点y的边
         */
        LinkedList<Integer>[] adj = adj(conditions, k);
        // 拓扑排序
        for (int i = 1; i < k + 1; i++) {
            if (!marked[i]) {
                checkCycle(adj, i);
            }
        }
        if (hasCycle) {
            return null;
        }
        // mapped[i]=j，数字i的索引为j
        int[] mapped = new int[k + 1];
        int idx = 0;
        while (!reversePostStack.isEmpty()) {
            mapped[reversePostStack.pop()] = idx;
            idx++;
        }
        return mapped;
    }

    private void checkCycle(LinkedList<Integer>[] adj, int vertex) {
        marked[vertex] = true;
        onStack[vertex] = true;
        for (int adjVertex : adj[vertex]) {
            if (hasCycle) {
                return;
            } else if (!marked[adjVertex]) {
                checkCycle(adj, adjVertex);
            } else if (onStack[adjVertex]){
                hasCycle = true;
                return;
            }
        }
        onStack[vertex] = false;
        reversePostStack.push(vertex);
    }

    private LinkedList<Integer>[] adj(int[][] conditions, int k) {
        LinkedList<Integer>[] adj = new LinkedList[k + 1];
        for (int i = 1; i < k + 1; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] condition : conditions) {
            int start = condition[0], end = condition[1];
            adj[start].add(end);
        }
        return adj;
    }
}
