package chapter1_Fundamentals.chapter1_5_UnionFind;

/**
 * 速度最快，在quick-union方法上改良
 */
public class UF_WeightedQuickUnion extends UF{
    //由触点索引的，每个根触点对应的分量中触点个数
    protected int[] treeSize;

    public UF_WeightedQuickUnion(int length) {
        super(length);
        for (int i=0;i<treeSize.length;i++){
            treeSize[i]=1;
        }
    }

    /**
     * 1.记录每一棵树的大小（分量中触点的个数）
     * 2.总是将较小的树连接到较大的树上
     * 构造结束后，任意节点的深度最多为lgN，深度降低，则访问次数减少
     * 参见笔记/书146页
     */
    @Override
    public void union(int point1, int point2) {
        int point1Root=find(point1);
        int point2Root=find(point2);
        if (point1Root==point2Root){
            return;
        }
        //将小树连接到大树上
        if (treeSize[point1Root]>treeSize[point2Root]){
            treeSize[point1Root]+=treeSize[point2Root];
            connectedComponentId[point2Root]=point1Root;
        }else{
            treeSize[point2Root]+=treeSize[point1Root];
            connectedComponentId[point1Root]=point2Root;
        }
        count--;
    }

    /**
     * 方法同quick-union中方法，查找到根触点(connectedComponentId[point]=point)
     */
    @Override
    public int find(int point) {
        while (connectedComponentId[point]!=point){
            point=connectedComponentId[point];
        }
        return point;
    }
}
