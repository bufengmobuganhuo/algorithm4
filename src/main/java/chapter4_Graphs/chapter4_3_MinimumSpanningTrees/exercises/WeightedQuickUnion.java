package chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/8/7 11:27 上午
 * TODO
 */
public class WeightedQuickUnion {
    private int count;
    private int[] connectedComponentId;
    private int[] treeSize;

    public WeightedQuickUnion(int length) {
        count=length;
        connectedComponentId=new int[length];
        treeSize=new int[length];
        Arrays.fill(treeSize,1);
        // 一开始都没有连上
        for (int i = 0; i < connectedComponentId.length; i++) {
            connectedComponentId[i]=i;
        }
    }

    public void connect(int point1,int point2){
        int connectId1=find(point1);
        int connectId2=find(point2);
        if (connectId1==connectId2){
            return;
        }
        // 小的连接到大的上
        if (treeSize[connectId1]>treeSize[connectId2]){
            treeSize[connectId1]+=treeSize[connectId2];
            connectedComponentId[connectId2]=connectId1;
        }else{
            treeSize[connectId2]+=treeSize[connectId1];
            connectedComponentId[connectId1]=connectId2;
        }
        count--;
    }

    /**
     * 找到point所在分量的根结点id
     * @param point
     * @return
     */
    public int find(int point){
        while(point!=connectedComponentId[point]){
            point=connectedComponentId[point];
        }
        return point;
    }

    public boolean connected(int vertex1,int vertex2){
        return find(vertex1)==find(vertex2);
    }
}
