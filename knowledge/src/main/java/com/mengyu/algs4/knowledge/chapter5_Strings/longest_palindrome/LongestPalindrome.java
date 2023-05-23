package com.mengyu.algs4.knowledge.chapter5_Strings.longest_palindrome;

/**
 * @author yu zhang
 * 最长回文子串
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(dp("babad"));
    }

    /**
     * 使用马拉车算法
     */
    public static String manacher(String s) {
        StringBuilder txt = new StringBuilder("$#");
        for (int i = 0; i < s.length(); i++) {
            txt.append(s.charAt(i));
            txt.append("#");
        }
        txt.append("@");
        int mx = 0, id = 0;
        int resCenter = 0, resLen = 0;
        int[] p = new int[txt.length()];
        // 因为加了哨兵，忽略首尾两个字符
        for (int i = 1; i < txt.length() - 1; i++) {
            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
            // 保证边界的情况下（忽略首尾字符），向两边扩展
            while (i - p[i] > 0 && i + p[i] < txt.length() - 1 && txt.charAt(i - p[i]) == txt.charAt(i + p[i])) {
                p[i]++;
            }
            // 更新最右边界和对应的中心
            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }
            // 更新结果
            if (resLen < p[i]) {
                resLen = p[i];
                resCenter = i;
            }
        }
        return s.substring((resCenter - resLen) / 2, (resCenter - resLen) / 2 + resLen - 1);
    }

    /**
     * 对于每个字符，以它为中心向两边扩展，更新回文串的长度
     */
    public static String expandFromCenter(String s) {
        int start = 0, end = 0;
        // 向两边分别扩展
        for (int i = 0; i < s.length(); i++) {
            // 奇数的情况 111 2 111
            int len1 = expandFromCenter(s, i, i);
            // 偶数的情况 1111 1111
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len >= end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandFromCenter(String s, int leftPtr, int rightPtr) {
        while (leftPtr >= 0 && rightPtr < s.length() && s.charAt(leftPtr) == s.charAt(rightPtr)) {
            leftPtr--;
            rightPtr++;
        }
        // 多算了一次
        return rightPtr - leftPtr - 1;
    }

    /**
     * 1. dp[i][j]：s[i...j]是不是回文串
     * 2. 状态转移方程：
     *  dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j]
     * 3. 可以看到是i依赖于i+1，所以这里让i从大 -> 小
     */
    public static String dp(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0, end = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - 1) - (i + 1) <= 0 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > end - start + 1) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
