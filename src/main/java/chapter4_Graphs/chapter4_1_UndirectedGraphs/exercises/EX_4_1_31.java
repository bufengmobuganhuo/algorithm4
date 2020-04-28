package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/4/28 15:29
 * 练习4.1.31：检测平行边
 */
public class EX_4_1_31 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyG.txt";
        Graph graph=new Graph(new In(path));
        EX_4_1_31 ex_4_1_31=new EX_4_1_31(graph);
        System.out.println(ex_4_1_31.count());
    }
    private final Graph graph;

    public EX_4_1_31(Graph graph) {
        this.graph = graph;
    }

    public int count(){
        return bfs(0);
    }

    private int bfs(int start){
        int count=0;
        Queue<Integer> queue=new LinkedList<>();
        int[] edgeTo=new int[graph.getVertexNum()];
        Arrays.fill(edgeTo,-1);
        queue.add(start);
        edgeTo[0]=0;
        while (!queue.isEmpty()){
            int tempStart=queue.poll();
            for (int vertex:graph.adj(tempStart)){
                if (edgeTo[vertex]==-1){
                    edgeTo[vertex]=tempStart;
                    queue.add(vertex);
                    //如果已经存在了一个tempStart -> vertex的路径，说明有平行边
                }else if(edgeTo[vertex]==tempStart){
                    count++;
                }
            }
        }
        return count;
    }
}
