package leetcode.unionfind;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/10/27 10:41 上午
 * TODO
 */
public class Ex947 {
    /**
     * 1. 对于一个指定的行（一行相当于一个根节点），只要这行上还有石头没有被移除，
     * 则这行对应列的石头（相当于链接了该根节点的子节点）就可以被删除。
     * 例如对于(2,2)上的石头，可以删除(2,0)(2,1)上的石头
     * 2. 对于一个指定的列（一列相当于一个根节点），只要这列上还有石头没有被移除，
     * 则这列对应行的石头（相当于链接了该根节点的子节点）就可以被移除。
     * 例如对于(2,2)上的石头，可以移除(0,2)(1,2)上的石头
     * 3. 综上，对于stones[i][j]，就需要使用并查集将i,j合并，但是因为并查集是一维的，所以使用j+10000代替j，合并i和j+10000
     * 4. 假设有M个集合，那么总共就需要留下M个石头，另N为石头总数，则可以移除的个数=N-M
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        int N = stones.length;
        UnionFind unionFin4 = new UnionFind(20000);
        for (int[] stone : stones) {
            unionFin4.connect(stone[0], stone[1] + 10000);
        }
        Set<Integer> rootSet = new HashSet<>();
        for (int[] stone : stones) {
            rootSet.add(unionFin4.find(stone[0]));
        }
        return N - rootSet.size();
    }

    static class UnionFind {
        int[] ids;

        public UnionFind(int length) {
            ids = new int[length];
            for (int i = 0; i < length; i++) {
                ids[i] = i;
            }
        }

        public void connect(int point1, int point2) {
            int point1Root = find(point1);
            int point2Root = find(point2);
            ids[point1Root] = point2Root;
        }

        private int find(int point) {
            while (point != ids[point]) {
                point = ids[point];
            }
            return point;
        }
    }
}
