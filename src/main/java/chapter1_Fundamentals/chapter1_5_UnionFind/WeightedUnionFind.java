package chapter1_Fundamentals.chapter1_5_UnionFind;

import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

/**
 * @author zhangyu
 * 2020/5/21 11:34
 * 加权 union-find 第三次尝试
 */
public class WeightedUnionFind {
    /**
     * 父连接数组
     */
    private int[] connectedComponentId;
    private int[] size;
    /**
     * 连通分量个数
     */
    private int count;

    public WeightedUnionFind(int pointNum) {
        connectedComponentId= new int[pointNum];
        //开始时都是独立的
        for (int i=0;i<connectedComponentId.length;i++){
            connectedComponentId[i]=i;
        }
        size=new int[pointNum];
        for (int i=0;i<size.length;i++){
            size[i]=1;
        }
        count=pointNum;
    }

    public void union(int point1,int point2){
        int root1=find(point1);
        int root2=find(point2);
        if (root1==root2){
            return;
        }
        if (size[root1]>size[root2]){
            connectedComponentId[root2]=root1;
            size[root1]+=size[root2];
        }else{
            connectedComponentId[root1]=root2;
            size[root2]+=size[root1];
        }
        count--;
    }

    public int find(int target){
        while (connectedComponentId[target]!=target){
            target=connectedComponentId[target];
        }
        return target;
    }
}
