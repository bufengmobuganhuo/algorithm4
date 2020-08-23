package leetcode.graph;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/15 11:09 上午
 * TODO
 */
public class Ex886 {
    public static void main(String[] args) {
        int[][] dislikes =
                {{1,2},{2,3},{3,4},{4,5},{1,5}};
        Ex886 ex886 = new Ex886();
        System.out.println(ex886.possibleBipartition(5, dislikes));
    }

    private boolean[] marked;
    private boolean[] color;
    private boolean isColorable;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        marked=new boolean[N+1];
        color=new boolean[N+1];
        isColorable=true;
        List<List<Integer>> graph=buildGraph(N,dislikes);
        for (int i = 1; i < graph.size(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
            if (!isColorable){
                return isColorable;
            }
        }
        return isColorable;
    }

    private void dfs(List<List<Integer>> graph, int startVertex) {
        marked[startVertex] = true;
        for (int adjVertex : graph.get(startVertex)) {
            if (!marked[adjVertex]) {
                color[adjVertex]=!color[startVertex];
                dfs(graph, adjVertex);
            }else if (color[adjVertex]==color[startVertex]){
                isColorable=false;
                return;
            }
        }
    }

    private List<List<Integer>> buildGraph(int N, int[][] dislikes){
        List<List<Integer>> graph=new ArrayList<>(N+1);
        graph.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] dislike : dislikes) {
            graph.get(dislike[0]).add(dislike[1]);
            graph.get(dislike[1]).add(dislike[0]);
        }
        return graph;
    }
}
