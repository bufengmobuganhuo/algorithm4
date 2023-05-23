package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

import edu.princeton.cs.algs4.StdOut;

/**
 * 最长递增子序列
 */
public class Ex2_IncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr={2,1,5,3,6,4,8,9,7};
        StdOut.println(solution(arr));
    }
    public static int solution(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        //dp[i]：表示必须以arr[i]结尾的最长递增子序列的长度
        int[] dp=new int[arr.length];
        dp[0]=1;
        int res=1;
        for (int i=1;i<arr.length;i++){
            int maxLen=0;
            //对于dp[i]：向前查找dp，以查找一个arr[j]<arr[i]，并且dp[j]是dp[0...i-1]中最大的长度
            for (int j=0;j<i;j++){
                if (arr[j]<arr[i]&&maxLen<dp[j]){
                    maxLen=dp[j];
                }
            }
            //如果没找到（arr[i]是arr[0...i]中最小的，则dp[i]=1）
            dp[i]=maxLen+1;
            res=Math.max(res,dp[i]);
        }
        return res;
    }
}
