package com.mengyu.algs4.knowledge.chapter6_Background;

/**
 * @author yuzhang
 * @date 2020/6/22 11:48 上午
 * 剩余网络中的边
 */
public class FlowEdge {
    /**
     * 起点
     */
    private final int v;
    /**
     * 终点
     */
    private final int w;
    /**
     * 容量
     */
    private final double capacity;
    /**
     * 流量
     */
    private double flow;

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return this.w;
    }

    public double capacity() {
        return this.capacity;
    }

    public double flow() {
        return this.flow;
    }

    public int other(int vertex) {
        return vertex == v ? w : v;
    }

    /**
     * @param vertex 原网络中，顶点vertex的剩余容量
     * @return
     */
    public double residualCapacityTo(int vertex) {
        //如果当前边和顶点vertex同向，说明当前边是正向边，正向边表示的是剩余容量
        if (vertex == v) {
            return flow;
            //如果是反向，说明是逆向边，逆向边表示的是实际流量
        } else if (vertex == w) {
            return capacity - flow;
        }
        throw new IllegalArgumentException();
    }

    /**
     * @param vertex 将原图中vertex的流量增加delta
     * @param delta
     */
    public void addResidualFlowTo(int vertex, double delta) {
        //正向边表示的是剩余容量，所以给原网络增加流量，那么剩余流量减少
        if (vertex == v) {
            flow -= delta;
        } else if (vertex == w) {
            flow += delta;
        }
    }

    @Override
    public String toString() {
        return String.format("%d->%d 容量：%.2f 流量：%.2f",v,w,capacity,flow);
    }
}
