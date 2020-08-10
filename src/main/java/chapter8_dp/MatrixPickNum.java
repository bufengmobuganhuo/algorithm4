package chapter8_dp;

/**
 * @author yuzhang
 * @date 2020/8/5 11:19 上午
 * 矩阵取数问题
 */
public class MatrixPickNum {
    public static void main(String[] args) {
        int[][] nums={
                {1,3,3},
                {2,1,3},
                {2,1,1}
        };
        MatrixPickNum matrixPickNum=new MatrixPickNum();
        System.out.println(matrixPickNum.maxSum(nums));
    }
    public int maxSum(int[][] nums){
        if (nums==null||nums.length==0||nums[0]==null||nums[0].length==0){
            return 0;
        }
        // 状态转移矩阵:dp[i][j]当到达第i行，第j列时，能取到的最大值
        int[][] dp=new int[nums.length][nums.length];
        // 初始条件
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                // 边界条件
                if (i==0){
                    dp[i][j]=nums[i][j]+(j>0?dp[i][j-1]:0);
                }else if (j==0){
                    dp[i][j]=nums[i][j]+dp[i-1][j];
                }else{
                    dp[i][j]=nums[i][j]+Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[nums.length-1][nums.length-1];
    }
}
