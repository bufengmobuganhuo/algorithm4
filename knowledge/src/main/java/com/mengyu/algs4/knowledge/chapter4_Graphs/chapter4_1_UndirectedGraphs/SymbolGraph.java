package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/4/21 16:48
 * TODO
 */
public class SymbolGraph {
    /**
     * 顶点名 -> 索引
     */
    private Map<String,Integer> vertexNameToIndex;
    /**
     * 索引 -> 顶点名
     */
    private String[] vertexIndexToName;
    private Digraph graph;

    public SymbolGraph(String filePath,String splitStr) {
        vertexNameToIndex=new HashMap<>();
        In in=new In(filePath);
        //构建 顶点名 -> 索引
        while (in.hasNextLine()){
            String[] vertexes=in.readLine().split(splitStr);
            for (int i=0;i<vertexes.length;i++){
                if (!vertexNameToIndex.containsKey(vertexes[i])){
                    vertexNameToIndex.put(vertexes[i],vertexNameToIndex.size());
                }
            }
        }
        //构建 索引 -> 顶点名
        vertexIndexToName=new String[vertexNameToIndex.size()];
        for (String vertexName:vertexNameToIndex.keySet()){
            vertexIndexToName[vertexNameToIndex.get(vertexName)]=vertexName;
        }

        //构建图
        graph=new Digraph(vertexIndexToName.length);
        in=new In(filePath);
        while (in.hasNextLine()){
            String[] vertexes=in.readLine().split(splitStr);
            String vertex1=vertexes[0];
            for (int i=1;i<vertexes.length;i++){
                graph.addEdge(vertexNameToIndex.get(vertex1),vertexNameToIndex.get(vertexes[i]));
            }
        }
    }

    /**
     * @param vertex
     * @return vertex是否是一个顶点
     */
    public boolean contains(String vertex){
        return vertexNameToIndex.containsKey(vertex);
    }

    /**
     * @param vertex
     * @return vertex的索引
     */
    public int index(String vertex){
        return vertexNameToIndex.get(vertex);
    }

    /**
     * @param index
     * @return 索引对应的顶点名
     */
    public String name(int index){
        return vertexIndexToName[index];
    }

    public Digraph getGraph(){
        return graph;
    }
}
