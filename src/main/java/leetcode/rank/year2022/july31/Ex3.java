package leetcode.rank.year2022.july31;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2022/7/31 11:09
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] edges = {1, 2, -1};
        System.out.println(new Ex3().closestMeetingNode(edges, 0, 2));
    }
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if (node1 == node2) {
            return node1;
        }
        int[] distTo1 = bfs(edges, node1);
        int[] distTo2 = bfs(edges, node2);
        int ans = Integer.MAX_VALUE, node = -1;
        for (int i = 0; i < distTo1.length; i++) {
            if (i == node1) {
                if (ans > distTo1[node2]) {
                    ans = distTo1[node2];
                    if (distTo2[node1] != Integer.MAX_VALUE) {
                        node = node1;
                    } else {
                        node = node2;
                    }
                }
            } else if (i == node2) {
                if (ans > distTo2[node1]) {
                    ans = distTo2[node1];
                    if (distTo1[node2] != Integer.MAX_VALUE) {
                        node = node1;
                    } else {
                        node = node1;
                    }
                }
            }
            if (distTo1[i] == Integer.MAX_VALUE || distTo2[i] == Integer.MAX_VALUE) {
                continue;
            }
            int dist = Math.max(distTo2[i], distTo1[i]);
            if (dist < ans) {
                ans = dist;
                node = i;
            } else if (dist == ans && node > i) {
                node = i;
            }
        }
        return node;
    }

    private int[] bfs(int[] edges, int startNode) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(startNode);
        int[] distTo = new int[edges.length];
        boolean[] marked = new boolean[edges.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[startNode] = 0;
        marked[startNode] = true;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int node = que.poll();
                if (edges[node] > -1 && !marked[edges[node]]) {
                    distTo[edges[node]] = distTo[node] + 1;
                    que.offer(edges[node]);
                    marked[edges[node]] = true;
                }
            }
        }
        return distTo;
    }
}
