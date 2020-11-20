package chapter4_Graphs.chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/18 上午11:18
 * TODO
 */
public class KosarajuSCC2 {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyDG";
        In in = new In(path);
        Digraph digraph = new Digraph(in);
        KosarajuSCC2 kosarajuSCC = new KosarajuSCC2(digraph);
        System.out.print(kosarajuSCC.isStronglyConnected(2, 3));
        System.out.println(kosarajuSCC.getCount());
    }

    private Stack<Integer> reversePostOrder;
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC2(Digraph digraph) {
        getOrder(digraph.reverse());
        marked = new boolean[digraph.getVertexNum()];
        id = new int[digraph.getVertexNum()];
        while (!reversePostOrder.isEmpty()) {
            int vertex = reversePostOrder.pop();
            if (!marked[vertex]) {
                dfs(digraph, vertex);
                count++;
            }
        }
    }

    public int getCount() {
        return count;
    }

    public boolean isStronglyConnected(int vertex1, int vertex2) {
        return id[vertex1] == id[vertex2];
    }

    private void dfs(Digraph digraph, int startVertex) {
        marked[startVertex] = true;
        id[startVertex] = count;
        for (int adjVertex : digraph.adj(startVertex)) {
            if (!marked[adjVertex]) {
                dfs(digraph, adjVertex);
            }
        }
    }

    private void getOrder(Digraph digraph) {
        reversePostOrder = new Stack<>();
        marked = new boolean[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]) {
                getOrder(digraph, i);
            }
        }
    }

    private void getOrder(Digraph digraph, int startVertex) {
        marked[startVertex] = true;
        for (int adjVertex : digraph.adj(startVertex)) {
            if (!marked[adjVertex]) {
                getOrder(digraph, adjVertex);
            }
        }
        reversePostOrder.push(startVertex);
    }
}
