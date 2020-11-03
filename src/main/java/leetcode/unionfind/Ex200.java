package leetcode.unionfind;

/**
 * @author yuzhang
 * @date 2020/10/28 9:50 上午
 * TODO
 */
public class Ex200 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
        };
        Ex200 ex200 = new Ex200();
        System.out.println(ex200.numIslands(grid));
    }
    public int numIslands(char[][] grid) {
        int zeroCount = 0;
        int rowNum = grid.length;
        int colNum = grid[0].length;
        UnionFind unionFind = new UnionFind(rowNum * colNum);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '1') {
                    zeroCount++;
                    continue;
                }
                int id = i * colNum + j;
                // 上方
                if (i > 0 && grid[i - 1][j] == '1') {
                    unionFind.union(id, id - colNum);
                }
                // 下方
                if (i < rowNum - 1 && grid[i + 1][j] == '1') {
                    unionFind.union(id, id + colNum);
                }
                // 左边
                if (j > 0 && grid[i][j - 1] == '1') {
                    unionFind.union(id, id - 1);
                }
                // 右边
                if (j < colNum - 1 && grid[i][j + 1] == '1') {
                    unionFind.union(id, id + 1);
                }
            }
        }
        return unionFind.count-zeroCount;
    }

    static class UnionFind {
        int[] weights;
        int[] ids;
        int count;

        public UnionFind(int length) {
            weights = new int[length];
            ids = new int[length];
            for (int i = 0; i < length; i++) {
                weights[i] = 1;
                ids[i] = i;
            }
            count = length;
        }

        public void union(int point1, int point2) {
            int point1Root = find(point1);
            int point2Root = find(point2);
            if (point1Root == point2Root) {
                return;
            }
            if (weights[point1Root] > weights[point2Root]) {
                weights[point1Root] += weights[point2Root];
                ids[point2Root] = point1Root;
            } else {
                weights[point2Root] += weights[point1Root];
                ids[point1Root] = point2Root;
            }
            count--;
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
