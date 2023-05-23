package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1202 {
    private int[] rootIds;

    private int[] weights;

    /**
     * 1. 将每个索引对看成是两个联通的点，则维护一个并查集，集合内的点之间可以互相转化
     * 2. 那么可以将同一个联通分量内的字符排序
     * 3. 从左到右遍历字符串，对于索引i，每次都用其所在联通分量中最小字符替换，则会达到一个最小值
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        weights = new int[n];
        rootIds = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = 1;
            rootIds[i] = i;
        }
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }
        // 并查集中联通分量的根 -> 联通分量内字符组成的字符串
        Map<Integer, PriorityQueue<Character>> idxToCharList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int rootId = find(i);
            // 使用优先队列，则每个堆顶都是最小的元素
            idxToCharList.computeIfAbsent(rootId, key -> new PriorityQueue<>()).offer(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int rootId = find(i);
            // 获取到i对应的联通分量内的字符串
            PriorityQueue<Character> chrList = idxToCharList.get(rootId);
            sb.append(chrList.poll());
        }
        return sb.toString();
    }

    private int find(int point) {
        while (point != rootIds[point]) {
            point = rootIds[rootIds[point]];
        }
        return point;
    }

    private void union(int point1, int point2) {
        int root1 = find(point1);
        int root2 = find(point2);
        if (root1 == root2) {
            return;
        }
        if (weights[root1] > weights[root2]) {
            weights[root1] += weights[root2];
            rootIds[root2] = root1;
        } else {
            weights[root2] += weights[root1];
            rootIds[root1] = root2;
        }
    }

}
