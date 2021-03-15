package leetcode.unionfind;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/1/25 上午11:35
 * TODO
 */
public class Ex947_1 {
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(20000);
        for (int[] stone : stones) {
            unionFind.connect(stone[0], stone[1] + 10000);
        }
        Set<Integer> unique = new HashSet<>();
        for (int[] stone : stones) {
            unique.add(unionFind.find(stone[0]));
        }
        return stones.length - unique.size();
    }

    static class UnionFind {
        int[] ids;
        int[] weights;

        public UnionFind(int cap) {
            ids = new int[cap];
            weights = new int[cap];
            for (int i = 0; i < cap; i++) {
                ids[i] = i;
                weights[i] = 1;
            }
        }

        public void connect(int point1, int point2) {
            int root1 = find(point1);
            int root2 = find(point2);
            if (root1 == root2) {
                return;
            }
            if (weights[root1] > weights[root2]) {
                weights[root1] += weights[root2];
                ids[root2] = ids[root1];
            } else {
                weights[root2] += weights[root1];
                ids[root1] = ids[root2];
            }
        }

        private int find(int point) {
            while (point != ids[point]) {
                ids[point] = ids[ids[point]];
                point = ids[point];
            }
            return point;
        }
    }
}
