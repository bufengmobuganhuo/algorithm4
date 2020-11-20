package chapter4_Graphs.chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/18 上午10:41
 * TODO
 */
public class Topological2 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        Topological2 topological=new Topological2(digraph);
        Stack<Integer> order= (Stack<Integer>) topological.order();
        while (!order.isEmpty()){
            System.out.print(order.pop()+",");
        }
    }
    private boolean[] marked;
    private Queue<Integer> preQue;
    private Queue<Integer> postQue;
    private Stack<Integer> reversePostStack;

    public Topological2(Digraph digraph) {
        DirectedCycle2 cycle2 = new DirectedCycle2(digraph);
        if (cycle2.hasCycle()) {
            throw new IllegalArgumentException();
        }
        marked = new boolean[digraph.getVertexNum()];
        preQue = new LinkedList<>();
        postQue = new LinkedList<>();
        reversePostStack = new Stack<>();
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph digraph, int startVertex) {
        preQue.offer(startVertex);
        marked[startVertex] = true;
        for (int adjVertex : digraph.adj(startVertex)) {
            if (!marked[adjVertex]) {
                dfs(digraph, adjVertex);
            }
        }
        postQue.offer(startVertex);
        reversePostStack.push(startVertex);
    }

    public Iterable<Integer> order(){
        return reversePostStack;
    }
}
