package chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/21 14:36
 * 广度优先搜索
 */
public class BreadthFirstPaths {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyG.txt";
        Graph graph=new Graph(new In(path));
        BreadthFirstPaths breadthFirstPaths=new BreadthFirstPaths(graph,0);
        Stack<Integer> paths= (Stack<Integer>) breadthFirstPaths.pathTo(5);
        while (!paths.isEmpty()){
            System.out.print(paths.pop()+"-");
        }
    }
    private boolean[] marked;
    private int[] edgeTo;
    private final int start;

    public BreadthFirstPaths(Graph graph,int start) {
        this.start = start;
        marked=new boolean[graph.getVertexNum()];
        edgeTo=new int[graph.getVertexNum()];
        bfs(graph,start);
    }

    private void bfs(Graph graph,int start){
        Queue<Integer> queue=new LinkedList<>();
        marked[start]=true;
        queue.add(start);
        while (!queue.isEmpty()){
            int vertex=queue.poll();
            for (int tempVertex:graph.adj(vertex)){
                if (!marked[tempVertex]){
                    marked[tempVertex]=true;
                    edgeTo[tempVertex]=vertex;
                    queue.add(tempVertex);
                }
            }
        }
    }

    public boolean hasPathTo(int target){
        return marked[target];
    }

    public Iterable<Integer> pathTo(int target){
        if (!hasPathTo(target)){
            return null;
        }
        Stack<Integer> path=new Stack<>();
        for (int tempVertex=target;tempVertex!=start;tempVertex=edgeTo[tempVertex]){
            path.push(tempVertex);
        }
        path.push(start);
        return path;
    }
}
