package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

import edu.princeton.cs.algs4.StdOut;

/**
 * 案例四：背包问题
 */
public class Ex4_BackpackProblem {
    public static void main(String[] args) {
        int[] value={3000,2000,1500};
        int[] weight={4,3,1};
        StdOut.println(solution(weight,value,5));
    }
    public static int solution(int[] weight,int[] value,int LoadBearing){
        if (value==null||value.length==0){
            return 0;
        }
        //dp[i][j]:当背包承重为j，可选物品为value[0...i]时可以获取的最大价值
        int[][] dp=new int[value.length][LoadBearing+1];
        //边界条件：当背包承重=0（j=0）时，获取的价值为0
        for (int i=0;i<value.length;i++){
            dp[i][0]=0;
        }
        //当只有一件物品时，只需要判断重量即可获得价值
        for (int i=0;i<LoadBearing+1;i++){
            dp[0][i]=i>=weight[0]?value[0]:0;
        }
        for (int i=1;i<value.length;i++){
            for (int j=1;j<LoadBearing+1;j++){
                //如果当前重量放不下当前物品，最大价值只能是value[0...i-1]中的最大值
                if (weight[i]>j){
                    dp[i][j]=dp[i-1][j];
                    //如果可以放下，则要比较value[0...i-1]中最大值和当前物品价值+剩余空间的价值
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],value[i]+dp[i-1][j-weight[i]]);
                }
            }
        }
        return dp[value.length-1][LoadBearing];
    }
}
