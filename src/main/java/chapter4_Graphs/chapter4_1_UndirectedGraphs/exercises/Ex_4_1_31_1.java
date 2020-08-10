package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/3 10:59 上午
 * 练习4.1.31：第一次尝试
 */
public class Ex_4_1_31_1 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Graph graph=new Graph(new In(path));
        Ex_4_1_31_1 ex_4_1_31_1=new Ex_4_1_31_1(graph);
        System.out.println(ex_4_1_31_1.count());
    }
    private Graph graph;

    public Ex_4_1_31_1(Graph graph) {
        this.graph = graph;
    }

    /**
     * 返回平行边个数
     * @return
     */
    public int count(){
        return bfs(0);
    }

    private int bfs(int startVertex){
        int count=0;
        int[] edgeTo=new int[graph.getVertexNum()];
        Arrays.fill(edgeTo,-1);
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(startVertex);
        edgeTo[startVertex]=startVertex;
        while (!queue.isEmpty()){
            int vertex=queue.poll();
            for (int adjVertex:graph.adj(vertex)){
                if (edgeTo[adjVertex]==-1){
                    edgeTo[adjVertex]=vertex;
                    queue.offer(adjVertex);
                }else if (edgeTo[adjVertex]==vertex){
                    count++;
                }
            }
        }
        return count;
    }
}
