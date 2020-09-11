package leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/2 8:43 上午
 * TODO
 */
public class Ex304 {
    public static void main(String[] args) {
        int[][] matrix = {
                {-4,-5},
        };
        Ex304 ex304 = new Ex304(matrix);
        System.out.println(ex304.sumRegion(0,1,0,1));
    }
    private int[][] dp;
    public Ex304(int[][] matrix) {
        if (matrix==null||matrix.length==0){
            return;
        }
        dp=new int[matrix.length][matrix[0].length];
        dp[0][0]=matrix[0][0];
        // 初始化条件
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i]+=matrix[0][i]+dp[0][i-1];
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0]+=matrix[i][0]+dp[i-1][0];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j]=matrix[i][j]+dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1];
            }
        }
    }

    public int sumRegion(int x, int y, int i, int j) {
        if (dp==null){
            return 0;
        }
        int upper = x>0?dp[x-1][j]:0;
        int left = 0;
        if (y>0){
            left = dp[i][y-1]-(x>0?dp[x-1][y-1]:0);
        }
        return dp[i][j]-upper-left;
    }
}
