package leetcode.rank.year2022.december04;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2022/12/4 10:03
 * TODO
 */
public class Ex3 {

    public static void main(String[] args) {
        int[][] roads = {
                {4, 5, 7468}, {6, 2, 7173}, {6, 3, 8365}, {2, 3, 7674}, {5, 6, 7852}, {1, 2, 8547}, {2, 4, 1885}, {2, 5,
                5192}, {1, 3, 4065}, {1, 4, 7357}
        };
        System.out.println(new Ex3().minScore(6, roads));
    }

    private boolean[] marked;

    private int ans;

    public int minScore(int n, int[][] roads) {
        ans = Integer.MAX_VALUE;
        marked = new boolean[n + 1];
        List<Edge>[] adj = adj(n, roads);
        // 因为一定有一条从1到n的路径，所以这里从1进行dfs，一定可以路过n，那么路上经过的路径中最小权重就是答案
        dfs(adj, 1);
        return ans;
    }

    private List<Edge>[] adj(int n, int[][] roads) {
        List<Edge>[] adj = new List[n + 1];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int[] road : roads) {
            adj[road[0]].add(new Edge(road[1], road[2]));
            adj[road[1]].add(new Edge(road[0], road[2]));
        }
        return adj;
    }

    private void dfs(List<Edge>[] adj, int startVertex) {
        marked[startVertex] = true;
        for (Edge edge : adj[startVertex]) {
            int to = edge.to;
            ans = Math.min(ans, edge.weight);
            if (!marked[to]) {
                dfs(adj, to);
            }
        }
    }


    private static class Edge {
        private int to;

        private int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
