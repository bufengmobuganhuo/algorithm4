package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/8/16 5:30 下午
 * TODO
 */
public class Ex1042 {
    public static void main(String[] args) {
        int[][] paths = {
                {6,4},{6,1},{3,1},{4,5},{2,1},{5,6},{5,2}
        };
        Ex1042 ex1042 = new Ex1042();
        System.out.println(Arrays.toString(ex1042.gardenNoAdj(6, paths)));
    }

    private int[] ans;

    public int[] gardenNoAdj(int N, int[][] paths) {
        if (N <= 0) {
            return new int[0];
        }
        ans = new int[N];
        List<Integer>[] graph = buildGraph(N, paths);
        for (int i = 0; i < N; i++) {
            if (ans[i]==0) {
                ans[i]=1;
                dfs(graph, i);
            }
        }
        return ans;
    }

    private void dfs(List<Integer>[] graph, int startVertex) {
        // 当前结点的颜色不能和他的相邻结点颜色一样
        ans[startVertex]=findUnUsedColor(startVertex,graph);
        for (int adjVertex : graph[startVertex]) {
            if (ans[adjVertex]==0) {
                dfs(graph, adjVertex);
            }
        }
    }

    private int findUnUsedColor(int vertex,List<Integer>[] graph){
        boolean[] used=new boolean[4];
        for (int adjVertex:graph[vertex]) {
            if (ans[adjVertex]!=0){
                used[ans[adjVertex]-1]=true;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!used[i]){
                return i+1;
            }
        }
        return -1;
    }

    private List<Integer>[] buildGraph(int N, int[][] paths) {
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] tuple : paths) {
            graph[tuple[0] - 1].add(tuple[1] - 1);
            graph[tuple[1] - 1].add(tuple[0] - 1);
        }
        return graph;
    }
}
