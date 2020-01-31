package chapter1_Fundamentals.chapter1_5_UnionFind;

/**
 * 练习1.5.20：使用链表实现quick-union方法
 */
public class UF_QuickUnionBasedOnDynamicArr extends UF{
    private int[] treeSize;

    public UF_QuickUnionBasedOnDynamicArr() {
        super(5);
        treeSize=new int[5];
        for (int i = 0;i<treeSize.length;i++){
            treeSize[i]=1;
        }
    }

    @Override
    public void union(int point1, int point2) {
        int maxPoint=Math.max(point1,point2);
        if (maxPoint>connectedComponentId.length){
            int[] tempNode=new int[2*maxPoint];
            int[] tempSize=new int[2*maxPoint];
            System.arraycopy(connectedComponentId,0,tempNode,0,connectedComponentId.length);
            System.arraycopy(treeSize,0,tempSize,0,treeSize.length);
            for (int i=connectedComponentId.length;i<tempNode.length;i++){
                tempNode[i]=i;
                tempSize[i]=1;
            }
            connectedComponentId=tempNode;
            treeSize=tempSize;
        }
        int point1Root=find(point1);
        int point2Root=find(point2);
        if (point1Root==point2Root){
            return;
        }
        if (treeSize[point1Root]>treeSize[point2Root]){
            treeSize[point1Root]+=treeSize[point2Root];
            connectedComponentId[point2Root]=point1Root;
        }else{
            treeSize[point2Root]+=treeSize[point1Root];
            connectedComponentId[point1Root]=point2Root;
        }
        count--;
    }

    @Override
    public int find(int point) {
        while (connectedComponentId[point]!=point){
            point=connectedComponentId[point];
        }
        return point;
    }
}
