package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind;

public abstract class UF {
    //分量的id，以触点作为索引(其长度是触点的个数)
    int[] connectedComponentId;
    //分量数量
    int count;

    public UF(int length) {
        //初始情况下，每个触点各自分隔，不连通
        count=length;
        connectedComponentId=new int[length];
        for (int i=0;i<length;i++){
            connectedComponentId[i]=i;
        }
    }

    /**
     * 判断point1和point2是否连通
     */
    public boolean connected(int point1,int point2){
        return find(point1)==find(point2);
    }

    /**
     * 将两个点所在的连通分量打通（在两个点之间增加一条连接）：
     */
    public abstract void union(int point1,int point2);

    /**
     * 查找触点point所在的连通分量的id
     */
    public abstract int find(int point);


}
