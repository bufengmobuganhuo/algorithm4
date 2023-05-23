package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind;

public class UF_QuickFind extends UF{
    public UF_QuickFind(int length) {
        super(length);
    }

    /**
     * 1.如果两个触点已经连通，则不作任何操作
     * 2.否则，将两个触点所在的连通分量的id改成同一个值
     * 缺点：需要遍历数组来实现归并两个分量
     */
    @Override
    public void union(int point1, int point2) {
        //查找两个点所在连通分量的id
        int point1Id=find(point1);
        int point2Id=find(point2);
        //如果已经连通，不做操作
        if (point1Id==point2Id){
            return;
        }
        //遍历数组，将id为point1Id的改为point2Id
        for (int i=0;i<super.connectedComponentId.length;i++){
            connectedComponentId[i]=connectedComponentId[i]==point1Id?point2Id:connectedComponentId[i];
        }
        super.count--;
    }

    /**
     * 同一连通分量中的触点在connectedComponentId[]中的值必须全部相同
     */
    @Override
    public int find(int point) {
        return connectedComponentId[point];
    }
}
