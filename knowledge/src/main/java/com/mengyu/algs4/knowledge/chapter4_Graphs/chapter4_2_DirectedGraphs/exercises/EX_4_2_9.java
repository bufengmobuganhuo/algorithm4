package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;
import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/3 10:30
 * 练习4.2.9：判断是否是拓扑排序
 */
public class EX_4_2_9 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        Integer[] vertexes={
                8,7,2,3,0,6,9,11,12,10,5,4,1,
        };
        Integer[] standard={
                8,7,2,3,0,6,9,11,12,10,5,4,1,
        };
        EX_4_2_9 ex_4_2_9=new EX_4_2_9();
        for (int i=0;i<1000000;i++){
            ArrayUtil.shuffle(vertexes);
            if (ex_4_2_9.isTopolicalSort(digraph,vertexes)){
                System.out.println(Arrays.toString(vertexes));
            }
        }
    }
    public boolean isTopolicalSort(Digraph digraph, Integer[] vertexes){
        EX_4_2_7 ex_4_2_7=new EX_4_2_7(digraph);
        for (int vertex:vertexes){
            //拓扑排序中，所有顶点的入度为0
            if (ex_4_2_7.indegree(vertex)!=0){
                return false;
            }
        }
        return true;
    }
}
