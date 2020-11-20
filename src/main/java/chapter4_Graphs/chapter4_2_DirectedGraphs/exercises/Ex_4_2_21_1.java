package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author yuzhang
 * @date 2020/11/19 上午9:25
 * TODO
 */
public class Ex_4_2_21_1 {
    private final Digraph digraph;

    public Ex_4_2_21_1(Digraph digraph) {
        this.digraph = digraph;
    }

    public int lca(int vertex1, int vertex2) {
        Digraph reverse = digraph.reverse();
        List<Pair> track1 = bfs(reverse, vertex1);
        List<Pair> track2 = bfs(reverse, vertex2);
        Optional<Pair> res = track1.stream()
                .filter(track2::contains)
                .min(Comparator.comparingInt(o -> o.depth));
        return res.map(pair -> pair.vertex).orElse(-1);
    }

    private List<Pair> bfs(Digraph digraph, int vertex) {
        Queue<Pair> que = new LinkedList<>();
        boolean[] marked = new boolean[digraph.getVertexNum()];
        List<Pair> track = new ArrayList<>();
        marked[vertex] = true;
        Pair start = new Pair(vertex, 0);
        track.add(start);
        que.offer(start);
        while (!que.isEmpty()) {
            Pair pair = que.poll();
            for (int adjVertex : digraph.adj(vertex)) {
                if (!marked[adjVertex]) {
                    marked[adjVertex] = true;
                    Pair adjPair = new Pair(adjVertex, pair.depth + 1);
                    que.offer(adjPair);
                    track.add(adjPair);
                }
            }
        }
        return track;
    }

    static class Pair{
        int vertex;
        int depth;

        public Pair(int vertex, int depth) {
            this.vertex = vertex;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Ex_4_2_21.Pair && vertex==((Ex_4_2_21.Pair) obj).key;
        }
    }
}
