package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex310_2 {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {0, 4}, {2, 5}, {5, 6}, {3, 7}, {6, 8}, {8, 9}, {9, 10}};
        System.out.println(new Ex310_2().findMinHeightTrees(11, edges));
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        int[] degree = new int[n];
        List<Integer>[] adj = new List[n];
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            if (adj[edge[0]] == null) {
                adj[edge[0]] = new ArrayList<>();
            }
            adj[edge[0]].add(edge[1]);
            if (adj[edge[1]] == null) {
                adj[edge[1]] = new ArrayList<>();
            }
            adj[edge[1]].add(edge[0]);
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                que.offer(i);
            }
        }
        while (!que.isEmpty()) {
            res = new ArrayList<>();
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int vertex = que.poll();
                res.add(vertex);
                if (adj[vertex] == null) {
                    continue;
                }
                for (int adjVertex : adj[vertex]) {
                    degree[adjVertex]--;
                    if (degree[adjVertex] == 1) {
                        que.offer(adjVertex);
                    }
                }
            }
        }
        return res;
    }
}
