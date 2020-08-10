package chapter8_dp;

/**
 * @author yuzhang
 * @date 2020/8/5 10:55 上午
 * 数塔取数问题
 */
public class NumberTower {
    public static void main(String[] args) {
        int[][] nums={
                {5},
                {8,4},
                {3,6,9},
                {7,2,9,5}
        };
        NumberTower numberTower=new NumberTower();
        System.out.println(numberTower.maxSum(nums));
    }
    public int maxSum(int[][] nums){
        if (nums==null||nums.length==0||nums[0]==null||nums[0].length==0){
            return 0;
        }
        // 状态转移矩阵:dp[i][j]：当遍历到第i行，第j列时，能取到的最大和
        int[][] dp=new int[nums.length][nums.length];
        // 初始条件:第0行第0列的最大取数和是nums[0][0]
        dp[0][0]=nums[0][0];
        int maxSum=dp[0][0];
        // 从第1行开始遍历
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                // 状态转移方程：遍历到第i行，第j列时，能取到的最大和Fi,j=max{Fi-1,j-1，Fi-1,j}+Ai,j
                //考虑边界条件:如果j=0，则能到达他的只有Fi-1,j
                if (j==0){
                    dp[i][j]=dp[i-1][j]+nums[i][j];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-1])+nums[i][j];
                }
                maxSum=Math.max(dp[i][j],maxSum);
            }
        }
        return maxSum;
    }
}
