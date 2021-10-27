package leetcode.rank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/9/4 下午10:49
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[][] land = {
                {0, 1},
                {1, 0}
        };
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.findFarmland(land));
    }
    public int[][] findFarmland(int[][] land) {
        int m = land.length, n = land[0].length;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] != 1) {
                    continue;
                }
                int tmpI = i, tmpJ = j;
                int leftEnd = j;
                while (tmpI < m && land[tmpI][j] == 1) {
                    tmpJ = j;
                    while (tmpJ < n && land[tmpI][tmpJ] == 1) {
                        land[tmpI][tmpJ] = 2;
                        leftEnd = tmpJ;
                        tmpJ++;
                    }
                    tmpI++;
                }
                res.add(new int[]{i, j, tmpI - 1, leftEnd});
            }
        }
        int[][] arr = new int[res.size()][4];
        return res.toArray(arr);
    }
}
