package com.mengyu.algs4.exercise.offer.dp;

/**
 * @author yu zhang
 */
public class Ex96 {
    public static void main(String[] args) {
        System.out.println(new Ex96().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2.length() + 1];
        dp[0] = true;
        // dp[0][j]����Ҫs1, ֻҪs2
        for (int i = 1; i <= s2.length(); i++) {
            if (s2.charAt(i-1) == s3.charAt(i-1)) {
                dp[i] = dp[i-1];
            } else {
                break;
            }
        }
        for (int i = 1; i <= s1.length(); i++) {
            // dp[i][0]: ��Ҫs2��ֻҪs1
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= s2.length(); j++) {
                char chr1 = s1.charAt(i - 1);
                char chr2 = s2.charAt(j - 1);
                char chr = s3.charAt(i + j - 1);
                dp[j] = (chr1 == chr && dp[j]) || (chr2 == chr && dp[j - 1]);
            }
        }
        return dp[s2.length()];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if(s3.length() != m+n){
            return false;
        }
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int j = 1; j <= n; j++){
            if(s2.charAt(j-1) == s3.charAt(j-1)){
                dp[j] = dp[j-1];
            }else{
                break;
            }
        }
        for(int i = 1; i <= m; i++){
            char a = s1.charAt(i-1);
            dp[0] = dp[0] && (a == s3.charAt(i-1));
            for(int j = 1; j <= n; j++){
                char b = s2.charAt(j-1);
                char c = s3.charAt(i+j-1);
                dp[j] = (b == c && dp[j-1]) || (a == c && dp[j]);
            }
        }
        return dp[n];
    }
}
