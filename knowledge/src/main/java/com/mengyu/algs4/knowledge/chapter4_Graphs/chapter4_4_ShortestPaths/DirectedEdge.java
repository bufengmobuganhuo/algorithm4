package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

/**
 * @author zhangyu
 * 2020/5/13 11:43
 * 代表一条有向边
 */
public class DirectedEdge implements Comparable<DirectedEdge>{
    /**
     * 边的起点
     */
    private final int start;
    /**
     * 边的终点
     */
    private final int end;
    /**
     * 边的权重
     */
    private final double weight;

    public DirectedEdge(int start, int end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public double getWeight(){
        return weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(DirectedEdge o) {
        return (int) (this.getWeight()-o.getWeight());
    }
}
