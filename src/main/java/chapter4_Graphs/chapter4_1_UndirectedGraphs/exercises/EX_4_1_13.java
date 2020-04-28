package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/23 19:35
 * TODO
 */
public class EX_4_1_13{
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyG.txt";
        Graph graph=new Graph(new In(path));
        EX_4_1_13 breadthFirstPaths=new EX_4_1_13(graph,0);
        Stack<Integer> paths= (Stack<Integer>) breadthFirstPaths.pathTo(4);
        while (!paths.isEmpty()){
            System.out.print(paths.pop()+"-");
        }
        System.out.println();
        System.out.println(breadthFirstPaths.distTo(4
        ));
    }
    private boolean[] marked;
    private int[] edgeTo;
    private final int start;
    private int levelCount;
    private HashMap<Integer,Integer> pathLenMap;

    public EX_4_1_13(Graph graph, int start) {
        this.start=start;
        marked=new boolean[graph.getVertexNum()];
        edgeTo=new int[graph.getVertexNum()];
        pathLenMap=new HashMap<>(graph.getVertexNum());
        bfs(graph,start);
    }

    private void bfs(Graph graph,int start){
        marked[start]=true;
        Queue<Integer> queue=new LinkedList<>();
        queue.add(start);
        //一层中最后一个顶点
        int levelLastVertex=start;
        //保存在遍历过程中，遍历到的最后一个顶点
        int tempLevelLastVertex=start;
        while (!queue.isEmpty()){
            int tempStart=queue.poll();
            for (int vertex:graph.adj(tempStart)){
                if (!marked[vertex]){
                    queue.add(vertex);
                    edgeTo[vertex]=tempStart;
                    marked[vertex]=true;
                    pathLenMap.put(vertex, levelCount+1);
                    tempLevelLastVertex=vertex;
                }
            }
            //如果已经遍历到了同一层的最后一个顶点，
            // 则层数+1,将遍历到的最后一个顶点的最后一个“子顶点”赋值给同层最后一个顶点
            if (levelLastVertex==tempStart){
                levelCount++;
                levelLastVertex=tempLevelLastVertex;
            }
        }
    }

    public int distTo(int vertex){
        return pathLenMap.getOrDefault(vertex,-1);
    }

    public Iterable<Integer> pathTo(int vertex){
        if (!marked[vertex]){
            return null;
        }
        Stack<Integer> path=new Stack<>();
        for (int tempVertex=vertex;tempVertex!=start;tempVertex=edgeTo[tempVertex]){
            path.push(tempVertex);
        }
        path.push(start);
        return path;
    }
}
