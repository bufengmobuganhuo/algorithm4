package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.DepthFirstPaths;
import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/3 8:59 上午
 * 深度优先搜索：是否有路径，不管长短
 */
public class DepthFirstPathsTry {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Graph graph=new Graph(new In(path));
        DepthFirstPathsTry depthFirstPaths=new DepthFirstPathsTry(graph,0);
        Stack<Integer> paths= (Stack<Integer>) depthFirstPaths.pathTo(5);
        while (!paths.isEmpty()){
            System.out.print(paths.pop()+"-");
        }
    }
    private boolean[] marked;
    private int[] edgeTo;
    private int start;

    public DepthFirstPathsTry(Graph graph, int start) {
        this.start = start;
        marked=new boolean[graph.getVertexNum()];
        edgeTo=new int[graph.getVertexNum()];
        dfs(graph,start);
    }

    private void dfs(Graph graph,int start){
        marked[start]=true;
        for (Integer vertex:graph.adj(start)){
            if (!marked[vertex]){
                edgeTo[vertex]=start;
                dfs(graph,vertex);
            }
        }
    }

    public boolean hasPathTo(int vertex){
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex){
        if (!hasPathTo(vertex)){
            return null;
        }
        Stack<Integer> path=new Stack<>();
        for (int tmpVertex=vertex;tmpVertex!=start;tmpVertex=edgeTo[tmpVertex]){
            path.push(tmpVertex);
        }
        path.push(start);
        return path;
    }
}
