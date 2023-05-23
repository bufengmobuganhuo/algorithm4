package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

/**
 * 动态规划方法
 */
public class DynamicProgramming {
    public static int search(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<0){
            return 0;
        }
        int[][] dp=new int[arr.length][aim+1];
        /**
         * 初始化边界:
         *  1.只使用arr[i]组成0=>只有一种方法
         *  2.只使用arr[0]组成0,1,2,...,j,...,aim=>如果j%arr[0],则有一种方法
         * */
        for (int i=0;i<arr.length;i++){
            dp[i][0]=1;
        }
        for (int i=0;i<=aim;i++){
            dp[0][i]=i%arr[0]==0?1:0;
        }
        //arr[i]:代表使用arr[0...i]中的货币
        for (int i=1;i<dp.length;i++){
            //dp[i][j]:表示使用arr[0...i]中的货币组成钱数j的方法数
            for (int j=1;j<=aim;j++){
                /**
                 * 1.使用0张arr[i],剩下的用arr[0...i-1]组成j
                 * 2.使用1张arr[i],剩下的用arr[0...i-1]组成j-arr[i]
                 * 3.使用2张arr[i],剩下的用arr[0...i-1]组成j-2*arr[i]
                 * 以此类推：
                 * dp[i][j]=dp[i-1][j]+dp[i-1][j-1*arr[i]]+dp[i-1][j-2*arr[i]]+...+
                 * */
                //
                for (int k=0;j-k*arr[i]>=0;k++){
                    dp[i][j]+=dp[i-1][j-k*arr[i]];
                }
            }
        }
        return dp[arr.length-1][aim];
    }
}
