package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/3/1 上午9:29
 * TODO
 */
public class Ex_4_2_21_2 {
    private static boolean[] marked;

    public static int lca(Digraph digraph, int vertex1, int vertex2) {
        Digraph reverse = digraph.reverse();
        LinkedList<Pair> path1 = bfs(reverse, vertex1);
        LinkedList<Pair> path2 = bfs(reverse, vertex2);
        Optional<Pair> acestor = path1.stream().filter(path2::contains).min(Comparator.comparingInt(x -> x.key));
        return acestor.map(pair -> pair.key).orElse(-1);
    }

    private static LinkedList<Pair> bfs(Digraph digraph, int vertex) {
        Queue<Pair> queue = new LinkedList<>();
        LinkedList<Pair> path = new LinkedList<>();
        marked[vertex] = true;
        queue.offer(new Pair(vertex, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int adjVertex : digraph.adj(pair.key)) {
                if (!marked[adjVertex]) {
                    Pair next = new Pair(adjVertex, pair.depth + 1);
                    path.add(next);
                    queue.offer(next);
                    marked[adjVertex] = true;
                }
            }
        }
        return path;
    }


    static class Pair {
        int key;
        int depth;

        public Pair(int key, int depth) {
            this.key = key;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pair && ((Pair) obj).key == this.key;
        }
    }
}
