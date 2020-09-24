package leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/24 2:33 下午
 * TODO
 */
public class Ex1143 {
    public static void main(String[] args) {
        Ex1143 ex1143 = new Ex1143();
        String text1 = "bc";
        String text2 = "yby";
        System.out.println(ex1143.longestCommonSubsequence2(text1, text2));
    }

    /**
     * 优化1：dp[i][j]只依赖于dp[i-1][j],dp[i-1][j-1],dp[i][j-1]
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        // 让较短的字符串作为text2，节省空间
        if (len1 < len2) {
            int tmp = len1;
            len1 = len2;
            len2 = tmp;
            String tmpStr = text1;
            text1 = text2;
            text2 = tmpStr;
        }
        int[] lastDp = new int[len2];
        int[] dp = new int[len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[j] = (i > 0 && j > 0 ? lastDp[j - 1] : 0) + 1;
                } else {
                    dp[j] = Math.max(j > 0 ? dp[j - 1] : 0, i > 0 ? lastDp[j] : 0);
                }
            }
            lastDp = dp;
            dp = new int[len2];
        }
        return lastDp[len2 - 1];
    }

    /**
     * 1. dp[i][j]：text1[0...i]和text2[0...j]之间的最长公共子序列
     * 2. 状态转移方程:如果text1[i]==text2[j]，则dp[i][j]=dp[i-1][j-1]+1；
     * 如果text1[i]!=text2[j]，则dp[i][j]=max{dp[i-1][j],dp[i][j-1]}
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1][len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = ((i > 0 && j > 0) ? dp[i - 1][j - 1] : 0) + 1;
                } else {
                    dp[i][j] = Math.max(i > 0 ? dp[i - 1][j] : 0, j > 0 ? dp[i][j - 1] : 0);
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}
