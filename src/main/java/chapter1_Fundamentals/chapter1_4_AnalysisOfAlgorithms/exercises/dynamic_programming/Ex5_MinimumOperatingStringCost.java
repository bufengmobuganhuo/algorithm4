package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

import edu.princeton.cs.algs4.StdOut;

/**
 * 案例五：操作字符串的最小代价
 */
public class Ex5_MinimumOperatingStringCost {
    public static void main(String[] args) {
        String str1="ab12cd3";
        String str2="abcdf";
        StdOut.println(solution(str1,str2,5,3,2));
    }

    public static int solution(String str1,String str2,int insertCost,int deleteCost,int replaceCost){
        if (str1==null||str2==null){
            return -1;
        }
        //dp[i][j]：将字符串str1[0...i-1]变换成str2[0...j-1]的最小代价
        //其中dp[0][0]:将""变换成""的最小代价
        int[][] dp=new int[str1.length()+1][str2.length()+1];
        //边界条件1：将str1变换成空字符串：一直删除
        for (int i=0;i<str1.length()+1;i++){
            dp[i][0]=i*deleteCost;
        }
        //边界条件2：将空字符串str1变换成str2：一直插入
        for (int i=0;i<str2.length()+1;i++){
            dp[0][i]=i*insertCost;
        }
        /**
         * 对于dp[i][j]:只可能来自于以下四种情况中的最小值
         * 1.str1[i]==str2[j]:
         *  只需要将str1[0..i-1]变换成str2[0...j-1]，即dp[i][j]=dp[i-1][j-1]
         * 2.str1[i]!=str2[j]:
         *  (1)将str1[0..i-1]变换成str2[0...j-1],然后再将str1[i]替换/(删除+插入)成str2[j]:
         *      dp[i][j]=dp[i-1][j-1]+min(replaceCost,deleteCost+insertCost)
         *  (2)删除str1[i],再将str1[0...i-1]变换为str2[0...j]:
         *      dp[i][j]=deleteCost+dp[i-1][j]
         *  (3)将str1[0...i]变换成str2[0...j-1]，再插入一个字符：
         *      dp[i][j]=dp[i][j-1]+insertCost
         * */
        replaceCost=Math.min(replaceCost,deleteCost+insertCost);
        for (int i=1;i<str1.length()+1;i++){
            for (int j=1;j<str2.length()+1;j++){
                char chr1=str1.charAt(i-1);
                char chr2=str2.charAt(j-1);
                if (chr1==chr2){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    int cost=Math.min(dp[i-1][j-1]+replaceCost,deleteCost+dp[i-1][j]);
                    dp[i][j]=Math.min(cost,dp[i][j-1]+insertCost);
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
