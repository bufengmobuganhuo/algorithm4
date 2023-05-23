package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind;

public class UF_QuickUnion extends UF{
    public UF_QuickUnion(int length) {
        super(length);
    }

    /**
     * 将两个触点的根触点变成同一个，则表示两个连通分量连接了起来
     * 最坏情况下访问数组次数=1+2*给定触点所对应的的节点的深度
     * 处理N对整数所需的访问数组总次数：2（1+2+3+...+N）~N^2
     */
    @Override
    public void union(int point1, int point2) {
        int point1Root=find(point1);
        int point2Root=find(point2);
        if(point1Root==point2Root){
            return;
        }
        connectedComponentId[point1Root]=point2Root;
        count--;
    }

    /**
     * 1.每个触点在connectedComponentId[]元素都是同一个分量中另一个触点的名称（也可能是他自己）（这种联系称为链接）
     * 2.从给定触点开始，由它链接到另一个触点，如此继续，会找到一个根触点，即这个根触点的链接指向他自己
     * （connectedComponentId[]=point）
     * 缺点：最坏情况下访问数组的次数=给定触点所对应的的节点的深度
     */
    @Override
    public int find(int point) {
        while (point!=connectedComponentId[point]){
            point=connectedComponentId[point];
        }
        return point;
    }
}
