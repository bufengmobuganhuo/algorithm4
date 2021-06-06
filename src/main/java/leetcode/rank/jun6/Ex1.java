package leetcode.rank.jun6;

/**
 * @author yuzhang
 * @date 2021/6/6 上午10:48
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[][] mat = {
                {0, 1},
                {1, 0}
        };
        int[][] target = {
                {1, 0},
                {0, 1}
        };
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.findRotation(mat, target));
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean res0 = true, res1 = true, res2 = true, res3 = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) {
                    res0 = false;
                }
                if (mat[i][j] != target[j][n - i - 1]) {
                    res1 = false;
                }
                if (mat[i][j] != target[n - i - 1][n - j - 1]) {
                    res2 = false;
                }
                if (mat[i][j] != target[n - j - 1][i]) {
                    res3 = false;
                }
            }
        }
        return res0 || res1 || res2 || res3;
    }
}
