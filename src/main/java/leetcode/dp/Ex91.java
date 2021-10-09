package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex91 {
    public static void main(String[] args) {
        Ex91 ex91 = new Ex91();
        System.out.println(ex91.numDecodings("12"));
    }
    /**
     * 1. dp[i] 表示s[1...i]时有几种解码方法
     * 2. 对于dp[i]有如下两种情况：
     * (1) 只使用s[i]一个字符(要求s[i] != 0)，那么dp[i] += dp[i - 1]
     * (2) 使用s[i-1], s[i]两个字符(要求s[i-1] != 0 && 10*s[i-1]+s[i] <= 26)，那么dp[i] += dp[i - 2]
     * 3. 初始条件 dp[0] = 1,空字符串只有一种编码方式
     * 4. 空间优化，dp[i]只依赖于dp[i-2]和dp[i-1]
     */
    public int numDecodings(String s) {
        int a = 0, b = 1, c = 0;
        for (int i = 1; i < s.length() + 1; i++) {
            c= 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && 10 * (s.charAt(i - 2) - '0') + s.charAt(i - 1) - '0' <= 26) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }
}
