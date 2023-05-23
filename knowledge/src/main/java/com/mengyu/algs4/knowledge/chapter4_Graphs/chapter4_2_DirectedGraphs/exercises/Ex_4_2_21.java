package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuzhang
 * @date 2020/8/6 9:04 上午
 * 练习4.2.21：有向无环图的最近共同祖先
 * 1. 找到原图的反向图：reverseDigraph，以下都是使用该反向图
 * 2. 从vertex1，vertex2分别开始广度优先遍历
 *  1⃣️ 在遍历过程中，使用一个链表记录vertex1，vertex2分别可达的顶点以及深度
 *  2⃣️ 那么深度最小的交集顶点就是这两个顶点的最近共同祖先
 */
public class Ex_4_2_21 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        Ex_4_2_21 ex_4_2_21=new Ex_4_2_21();
        System.out.println(ex_4_2_21.lca(digraph,10,4));
    }
    public int lca(Digraph digraph,int vertex1,int vertex2){
        Digraph reverseDigraph = digraph.reverse();
        List<Pair> reachableVertex1=bfs(reverseDigraph,vertex1);
        List<Pair> reachableVertex2=bfs(reverseDigraph,vertex2);
        Optional<Pair> res = reachableVertex1.stream().filter(reachableVertex2::contains)
                .collect(Collectors.toList()).stream()
                .min(Comparator.comparingInt(o -> o.depth));
        return res.map(pair -> pair.key).orElse(-1);
    }

    private List<Pair> bfs(Digraph digraph, int rootVertex){
        // 记录 <顶点，深度>
        Queue<Pair> queue = new LinkedList<>();
        // 顶点rootVertex可达的顶点及深度
        List<Pair> list=new ArrayList<>();
        boolean[] marked = new boolean[digraph.getVertexNum()];
        queue.offer(new Pair(rootVertex, 0));
        marked[rootVertex]=true;
        while (!queue.isEmpty()){
            Pair vertexPair = queue.poll();
            for (Integer adjVertex : digraph.adj(vertexPair.key)){
                if (!marked[adjVertex]){
                    Pair pair= new Pair(adjVertex, vertexPair.depth + 1);
                    queue.offer(pair);
                    list.add(pair);
                    marked[adjVertex]=true;
                }
            }
        }
        return list;
    }

    static class Pair{
        int key;
        int depth;

        public Pair(int key, int depth) {
            this.key = key;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pair && key==((Pair) obj).key;
        }
    }
}
