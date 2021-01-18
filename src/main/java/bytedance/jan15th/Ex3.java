package bytedance.jan15th;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/1/15 上午9:53
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 0}};
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.shortestPath(grid, 5));
    }

    /**
     * BFS
     *
     * @param grid
     * @param k
     * @return
     */
    public int shortestPath(int[][] grid, int k) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length, stepCount = 0;
        if (m == 1 && n == 1) {
            return 0;
        }
        // 可以向上下左右四个方向移动
        int[][] directs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<PositionInfo> que = new LinkedList<>();
        Set<PositionInfo> visited = new HashSet<>();
        PositionInfo start = new PositionInfo(0, 0, k);
        que.offer(start);
        visited.add(start);
        while (!que.isEmpty()) {
            stepCount++;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                PositionInfo positionInfo = que.poll();
                for (int[] direct : directs) {
                    // 要移动到的下一个位置
                    int mx = positionInfo.x + direct[0], ny = positionInfo.y + direct[1];
                    PositionInfo nextPositionWithoutBarrier = new PositionInfo(mx, ny, positionInfo.kLeft);
                    PositionInfo nextPosition = new PositionInfo(mx, ny, positionInfo.kLeft - 1);
                    if (mx >= 0 && mx < m && ny >= 0 && ny < n) {
                        // 如果要移动到的为位置没有障碍物(终点一定没有障碍物)
                        if (grid[mx][ny] == 0 && !visited.contains(nextPositionWithoutBarrier)) {
                            // 如果已经到达了目标位置
                            if (mx == m - 1 && ny == n - 1) {
                                return stepCount;
                            }
                            visited.add(nextPositionWithoutBarrier);
                            que.offer(nextPositionWithoutBarrier);
                            // 下一个位置可达才有判断价值
                        } else if (grid[mx][ny] == 1 && positionInfo.kLeft > 0 && !visited.contains(nextPosition)) {
                            visited.add(nextPosition);
                            que.offer(nextPosition);
                        }
                    }
                }
            }
        }
        return -1;
    }

    static class PositionInfo {
        int x;
        int y;
        int kLeft;

        public PositionInfo(int x, int y, int kLeft) {
            this.x = x;
            this.y = y;
            this.kLeft = kLeft;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PositionInfo) {
                return this.x == ((PositionInfo) obj).x
                        && this.y == ((PositionInfo) obj).y
                        && this.kLeft == ((PositionInfo) obj).kLeft;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, kLeft);
        }
    }
}
