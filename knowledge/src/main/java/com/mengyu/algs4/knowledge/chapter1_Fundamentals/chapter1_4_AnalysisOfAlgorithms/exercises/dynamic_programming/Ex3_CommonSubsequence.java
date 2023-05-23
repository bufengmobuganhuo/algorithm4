package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

import edu.princeton.cs.algs4.StdOut;

/**
 * 例题三：最长公共子序列
 */
public class Ex3_CommonSubsequence {
    public static void main(String[] args) {
        String str1="abcd";
        String str2="abcd";
        StdOut.println(solution(str1,str2));
    }
    public static int solution(String str1,String str2){
        if (str1==null||str2==null||str1.length()==0||str2.length()==0){
            return 0;
        }
        //dp[0...i][0...j]：表示str1[0-i]与str2[0-j]的最长公共子序列长度
        int[][] dp=new int[str1.length()][str2.length()];
        //边界条件: 查找str1[i]与str2[0]之间的公共子序列
        for (int i=0;i<str1.length();i++){
            dp[i][0]=str1.charAt(i)==str2.charAt(0)||(i-1>=0&&dp[i-1][0]==1)?1:0;
        }
        //边界条件：查找str1[0]与str2[i]之间的公共子序列
        for (int i=0;i<str2.length();i++){
            dp[0][i]=str1.charAt(0)==str2.charAt(i)||(i-1>=0&&dp[0][i-1]==1)?1:0;
        }
        for (int i=1;i<str1.length();i++){
            for (int j=1;j<str2.length();j++){
                /**
                 * 如果相等，说明公共子序列可以多一个元素，则dp[i][j]=dp[i-1][j-1]+1
                 * 因为这里查找的是str1[0...i]与str2[0...j]的公共子序列
                 * */
                if (str1.charAt(i)==str2.charAt(j)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    /**
                     * 否则最大长度保持不变
                     * */
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[str1.length()-1][str2.length()-1];
    }
}
