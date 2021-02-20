package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/1/27 上午11:28
 * TODO
 */
public class Ex5 {
    public static void main(String[] args) {
        Ex5 ex5 = new Ex5();
        System.out.println(ex5.longestPalindrome2("aab"));
    }

    /**
     * 1. 对于一个字符串：4 3 2 1 2 3 5，从回文中心"1"开始向两边扩展，只有当s(i)==s(j)时才能继续扩展，否则就不能
     * 2. 边界条件是子串长度为1或2的情况，那么可以枚举每个回文中心，从而获取到最长回文串
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 子字符串
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 向左右两边扩展回文串
    private int expandAroundCenter(String s, int leftPtr, int rightPtr) {
        while (leftPtr >= 0 && rightPtr < s.length() && s.charAt(leftPtr) == s.charAt(rightPtr)) {
            leftPtr--;
            rightPtr++;
        }
        return rightPtr - leftPtr - 1;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        String ans = "";
        // dp[i][j]: 字符串s{i...j}是否是回文字符串
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && ans.length() < j - i + 1) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
