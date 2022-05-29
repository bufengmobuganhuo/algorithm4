package leetcode.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex675 {
    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(2, 3, 4));
        forest.add(Arrays.asList(0, 0, 5));
        forest.add(Arrays.asList(8, 7, 6));
        System.out.println(new Ex675().cutOffTree(forest));
    }

    private static final int[][] dirs = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i,j});
                }
            }
        }
        // 按照树从低到高排序
        Collections.sort(trees, Comparator.comparingInt(o -> forest.get(o[0]).get(o[1])));
        // 计算相邻两棵树的最短距离
        int si = 0, sj = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); i++) {
            int step = bfs(forest, si, sj, trees.get(i)[0], trees.get(i)[1]);
            if (step == -1) {
                return -1;
            }
            ans += step;
            si = trees.get(i)[0];
            sj = trees.get(i)[1];
        }
        return ans;
    }

    private int bfs(List<List<Integer>> forest, int si, int sj, int ti, int tj) {
        if (si == ti && sj == tj) {
            return 0;
        }
        int m = forest.size(), n = forest.get(0).size();
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        que.offer(new int[]{si, sj});
        visited[si][sj] = true;
        int step = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] pos = que.poll();
                for (int j = 0; j < dirs.length; j++) {
                    int ni = pos[0] + dirs[j][0], nj = pos[1] + dirs[j][1];
                    if (ni == ti && nj == tj) {
                        return step;
                    }
                    if (ni >= 0 && nj >= 0 && ni < m && nj < n && !visited[ni][nj] && forest.get(ni).get(nj) != 0) {
                        visited[ni][nj] = true;
                        que.offer(new int[]{ni, nj});
                    }
                }
            }
        }
        return -1;
    }
}
