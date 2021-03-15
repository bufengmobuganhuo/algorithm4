package leetcode.recursive;

/**
 * @author yuzhang
 * @date 2021/5/18 上午8:00
 * TODO
 */
public class Ex70 {
    public static void main(String[] args) {
        Ex70 ex70 = new Ex70();
        System.out.println(ex70.climbStairs(3));
    }

    public int climbStairs(int n) {
        int[][] matrix = {{1, 1}, {1, 0}};
        int[][] c = pow(matrix, n);
        return c[0][0];
    }

    private int[][] pow(int[][] a, int n) {
        if (n == 1) {
            return a;
        }
        if (n % 2 == 1) {
            return multi(a, pow(a, n - 1));
        } else {
            int[][] c = pow(a, n / 2);
            return multi(c, c);
        }
    }

    private int[][] multi(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}
