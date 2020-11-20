package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/3 10:28 上午
 * TODO
 */
public class Ex_4_1_16_1 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph=new Digraph(new In(path));
        Ex_4_1_16_1 ex_4_1_16_1=new Ex_4_1_16_1(graph);
        System.out.println(ex_4_1_16_1.eccentricity(0));

    }
    private Digraph graph;

    public Ex_4_1_16_1(Digraph graph) {
        this.graph = graph;
    }

    public int eccentricity(int vertex) {
        int eccentricity = 0;
        // 一层中最后一个结点
        int lastVertexInLayer = vertex;
        // 遍历过程中访问到的最后一个结点
        int tmpVertexInLayer=vertex;
        boolean[] marked=new boolean[graph.getVertexNum()];
        marked[vertex]=true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            int startVertex=queue.poll();
            for (int adjVertex:graph.adj(startVertex)){
               if (!marked[adjVertex]){
                   queue.offer(adjVertex);
                   tmpVertexInLayer=adjVertex;
                   marked[adjVertex]=true;
               }
            }
            if (startVertex==lastVertexInLayer){
                lastVertexInLayer=tmpVertexInLayer;
                eccentricity++;
            }
        }
        return eccentricity;
    }

    public int diameter(){
        int maxEccentricity = Integer.MIN_VALUE;
        for (int i = 0; i < graph.getVertexNum(); i++) {
            maxEccentricity=Math.max(eccentricity(i),maxEccentricity);
        }
        return maxEccentricity;
    }

    public int radius(){
        int minEccentricity=Integer.MAX_VALUE;
        for (int i = 0; i < graph.getVertexNum(); i++) {
            minEccentricity=Math.min(eccentricity(i),minEccentricity);
        }
        return minEccentricity;
    }


}
