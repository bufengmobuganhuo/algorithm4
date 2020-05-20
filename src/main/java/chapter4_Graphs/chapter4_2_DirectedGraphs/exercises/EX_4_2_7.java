package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @author zhangyu
 * 2020/5/3 9:29
 * 练习4.2.7：计算顶点的度数
 */
public class EX_4_2_7 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        EX_4_2_7 ex_4_2_7=new EX_4_2_7(digraph);
        for (Integer vertex:ex_4_2_7.inDegreeMap.keySet()){
            System.out.println(vertex+"-"+ex_4_2_7.inDegreeMap.getOrDefault(vertex,0));
        }
        System.out.println(ex_4_2_7.isMap());
    }
    private Map<Integer,Integer> inDegreeMap;
    private boolean[] marked;
    private Digraph digraph;

    public EX_4_2_7(Digraph digraph) {
        inDegreeMap=new HashMap<>(digraph.getVertexNum());
        marked=new boolean[digraph.getVertexNum()];
        this.digraph=digraph;
        for (int i=0;i<digraph.getVertexNum();i++){
            if (!marked[i]){
                bfs(i);
            }
        }
    }

    private void bfs(int start){
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(start);
        marked[start]=true;
        while (!queue.isEmpty()){
            int tempStart=queue.poll();
            for (int vertex:digraph.adj(tempStart)){
                if (!marked[vertex]){
                    queue.offer(vertex);
                    marked[vertex]=true;
                    inDegreeMap.put(vertex,inDegreeMap.getOrDefault(vertex,0)+1);
                }else{
                    inDegreeMap.put(vertex,inDegreeMap.getOrDefault(vertex,0)+1);
                }
            }
        }
    }

    public int outdegree(int vertex){
        LinkedList<Integer> adj= (LinkedList<Integer>) digraph.adj(vertex);
        return adj.size();
    }

    public int indegree(int vertex){
        return inDegreeMap.getOrDefault(vertex,0);
    }

    public Iterable<Integer> sinks(){
        List<Integer> sinks=new ArrayList<>(digraph.getVertexNum());
        for (int i=0;i<digraph.getVertexNum();i++){
            LinkedList<Integer> adj= (LinkedList<Integer>) digraph.adj(i);
            if (adj.size()==0){
                sinks.add(i);
            }
        }
        return sinks;
    }

    public Iterable<Integer> sources(){
        List<Integer> sources=new ArrayList<>();
        for (Integer vertex:inDegreeMap.keySet()){
            if (inDegreeMap.getOrDefault(vertex,0)==0){
                sources.add(vertex);
            }
        }
        return sources;
    }

    public boolean isMap(){
        for (int i=0;i<digraph.getVertexNum();i++){
            LinkedList<Integer> adj= (LinkedList<Integer>) digraph.adj(i);
            if (adj.size()!=1){
                return false;
            }
        }
        return true;
    }
}
