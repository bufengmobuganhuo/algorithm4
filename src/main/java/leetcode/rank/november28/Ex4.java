package leetcode.rank.november28;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/11/28 11:07 上午
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        Ex4 ex4 = new Ex4();
        int[][] meetings = {
                {3, 4, 2},
                {1, 2, 1},
                {2, 3, 1}
        };
        System.out.println(ex4.findAllPeople(5, meetings, 1));
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        LinkedList<Edge>[] adj = buildGraph(n, meetings, firstPerson);
        int[] earliestNotified = new int[n];
        Arrays.fill(earliestNotified, Integer.MAX_VALUE);
        earliestNotified[0] = -1;
        Set<Integer> res = new HashSet<>();
        res.add(0);
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        while (!que.isEmpty()) {
            int start = que.poll();
            for (Edge adjEdge : adj[start]) {
                int end = adjEdge.other(start);
                if (earliestNotified[start] > adjEdge.time || earliestNotified[end] <= adjEdge.time) {
                    continue;
                }
                res.add(end);
                earliestNotified[end] = Math.min(earliestNotified[end], adjEdge.time);
                que.offer(adjEdge.other(start));
            }
        }
        return new ArrayList<>(res);
    }

    private LinkedList<Edge>[] buildGraph(int n, int[][] meetings, int firstPerson) {
        LinkedList<Edge>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        Edge firstEdge = new Edge(0, firstPerson, 0);
        adj[0].add(firstEdge);
        adj[firstPerson].add(firstEdge);
        for (int[] meeting : meetings) {
            Edge edge = new Edge(meeting[0], meeting[1], meeting[2]);
            adj[meeting[0]].add(edge);
            adj[meeting[1]].add(edge);
        }
        return adj;
    }

    private class Edge {
        int vertex1, vertex2, time;

        public Edge(int vertex1, int vertex2, int time) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.time = time;
        }

        public int other(int vertex) {
            return vertex == vertex1 ? vertex2 : vertex1;
        }
    }
}
