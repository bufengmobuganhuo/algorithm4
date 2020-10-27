package leetcode.unionfind;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/10/27 9:21 上午
 * TODO
 */
public class Ex547 {
    public static void main(String[] args) {
        int[][] M = {{1,1,0},
                     {1,1,1},
                     {0,1,1}};
        Ex547 ex547 = new Ex547();
        System.out.println(ex547.findCircleNum(M));
    }
    public int findCircleNum(int[][] M) {
        UnionFind unionFind = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j]==1){
                    unionFind.connect(i,j);
                }
            }
        }
        return unionFind.count;
    }

    static class UnionFind{
        int[] weight;
        int[] connectedComponentId;
        int count;

        public UnionFind(int length) {
            weight=new int[length];
            connectedComponentId=new int[length];
            for (int i = 0; i < length; i++) {
                weight[i]=1;
                connectedComponentId[i]=i;
            }
            count=length;
        }

        public void connect(int point1, int point2){
            int point1Root = find(point1);
            int point2Root = find(point2);
            if (point1Root==point2Root){
                return;
            }
            if (weight[point1Root]>weight[point2Root]){
                weight[point1Root]+=weight[point2Root];
                connectedComponentId[point2Root]=point1Root;
            }else {
                weight[point2Root]+=weight[point1Root];
                connectedComponentId[point1Root]=point2Root;
            }
            count--;
        }

        private int find(int point){
            while (point!=connectedComponentId[point]){
                connectedComponentId[point]=connectedComponentId[connectedComponentId[point]];
                point=connectedComponentId[point];
            }
            return point;
        }
    }
}
