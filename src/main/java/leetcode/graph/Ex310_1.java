package leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex310_1 {
    /**
     * 解释见：{@link Ex310}
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        int[] degrees = new int[n];
        List<Integer> ans = new ArrayList<>();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        // 构建邻接表
        for (int[] edge : edges) {
            int vertex1 = edge[0];
            int vertex2 = edge[1];
            degrees[vertex1]++;
            degrees[vertex2]++;
            adj.computeIfAbsent(vertex1, key -> new LinkedList<>()).add(vertex2);
            adj.computeIfAbsent(vertex2, key -> new LinkedList<>()).add(vertex1);
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 1) {
                ans.add(i);
                que.offer(i);
            }
        }
        while (!que.isEmpty()) {
            int size = que.size();
            ans.clear();
            for (int i = 0; i < size; i++) {
                int vertex = que.poll();
                ans.add(vertex);
                for (int adjVertex : adj.get(vertex)) {
                    degrees[adjVertex]--;
                    if (degrees[adjVertex] == 1){
                        que.offer(adjVertex);
                    }
                }
            }
        }
        return ans;
    }
}
