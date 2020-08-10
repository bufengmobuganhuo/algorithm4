package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/6 10:31 上午
 * 练习4.2.22：最短先导路径
 */
public class Ex_4_2_22_1 {
    public int shortestAncestorLength(Digraph digraph,int vertex1,int vertex2){
        // 用反向图做bfs，可以找到共同祖先
        int[] dist1 =bfs(digraph.reverse(),vertex1);
        int[] dist2 =bfs(digraph.reverse(),vertex2);
        int minDist=Integer.MAX_VALUE;
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            // 如果是共同祖先
            if (dist1[i]!=-1&&dist2[i]!=-1){
                // 找到最小和
                minDist=Math.min(dist1[i]+dist2[i],minDist);
            }
        }
        return minDist;
    }

    private int[] bfs(Digraph digraph,int vertex){
        int[] distTo =new int[digraph.getVertexNum()];
        Arrays.fill(distTo,-1);
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()){
            int start=queue.poll();
            for (int adjVertex:digraph.adj(start)){
                if (distTo[adjVertex]==-1){
                    distTo[adjVertex]=distTo[start]+1;
                    queue.offer(adjVertex);
                }
            }
        }
        return distTo;
    }
}
