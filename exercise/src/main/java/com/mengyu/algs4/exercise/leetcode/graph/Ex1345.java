package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex1345 {
    public static void main(String[] args) {
        int[] arr = {8, 8, 8, 8, 8, 404};
        System.out.println(new Ex1345().minJumps(arr));
    }

    public int minJumps(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> sameValueIdxes = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sameValueIdxes.computeIfAbsent(arr[i], key -> new ArrayList<>()).add(i);
        }
        boolean[] marked = new boolean[arr.length];
        int[] distTo = new int[arr.length];
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        marked[0] = true;
        while (!que.isEmpty()) {
            int vertex = que.poll();
            if (vertex == arr.length - 1) {
                return distTo[vertex];
            }
            if (sameValueIdxes.containsKey(arr[vertex])) {
                for (int sameVal : sameValueIdxes.get(arr[vertex])) {
                    if (!marked[sameVal]) {
                        que.offer(sameVal);
                        distTo[sameVal] = distTo[vertex] + 1;
                        marked[sameVal] = true;
                    }
                }
                sameValueIdxes.remove(arr[vertex]);
            }
            if (vertex - 1 >= 0 && !marked[vertex - 1]) {
                distTo[vertex - 1] = distTo[vertex] + 1;
                marked[vertex - 1] = true;
                que.offer(vertex - 1);
            }
            if (vertex + 1 < arr.length && !marked[vertex + 1]) {
                distTo[vertex + 1] = distTo[vertex] + 1;
                marked[vertex + 1] = true;
                que.offer(vertex + 1);
            }
        }
        return -1;
    }
}
