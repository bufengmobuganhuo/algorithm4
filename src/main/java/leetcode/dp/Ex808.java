package leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/21 9:00 上午
 * TODO
 */
public class Ex808 {
    public static void main(String[] args) {
        Ex808 ex808 = new Ex808();
        System.out.println(ex808.soupServings(100));
    }
    /**
     * 1. 将分配分属除以25，可得分配方案为(4,0),(3,1),(2,2),(1,3)
     * 2. 在一次分配中A平均分配(4+3+2+1)/4=2.5份，B平均分配(1+2+3)/4=1.5，所以当N很大时，A一定先分配完
     * 3. dp[i][j]:A剩i，B剩j时的概率
     * 4. 状态转移方程：dp[i][j]=0.25(dp[i-4][j]+dp[i-3][j-1]+dp[i-2][j-3]+dp[i-1][j-3])
     *
     * @param N
     * @return
     */
    public double soupServings(int N) {
        N = N / 25 + (N % 25 > 0 ? 1 : 0);
        //N很大时，A一定先分配完
        if (N >= 500) {
            return 1.0;
        }
        double[][] dp = new double[N + 1][N + 1];
        // sum：两种物品的总量
        for (int sum = 0; sum <= 2 * N; sum++) {
            for (int i = 0; i <= N; i++) {
                int j = sum - i;
                double ans = 0.0;
                // 边界条件1:B剩下的量需要符合基本要求
                if (j < 0 || j > N) {
                    continue;
                }
                // 边界条件2:A先分配完的概率=1
                if (i == 0) {
                    ans = 1.0;
                }
                // 边界条件3:A,B同时分配完的概率=1-（A先分配完的概率+A后分配完的概率）=1-(0.5(使用前两种方式，A一定先分配完)+0)=0.5
                if (i == 0 && j == 0) {
                    ans = 0.5;
                }
                if (i > 0 && j > 0) {
                    ans = 0.25 * (dp[max(i - 4)][j] +
                            dp[max(i - 3)][max(j - 1)] +
                            dp[max(i - 2)][max(j - 2)] +
                            dp[max(i - 1)][max(j - 3)]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N][N];
    }

    private int max(int x) {
        return Math.max(0, x);
    }
}
