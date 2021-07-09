package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex10 {
    /**
     * 1. 题目中的匹配实际上是一个逐个匹配的过程，前面的匹配结果会影响后面字符的匹配过程，所以使用动态规划
     * 2. 状态定义为：f[i][j]：s的前i个字符和p中前j个字符是否匹配
     * 3. 状态转移过程如下：
     * (1) 如果p[j]是一个字符：
     * 1. 若 s[i]=p[j]，则f[i][j]=f[i-1][j-1]，取决于上一个的匹配结果
     * 2. 若 s[i]!=p[j]，则f[i][j]=false
     * (2) 如果p[j]='.'，匹配任意一个字符：f[i][j]=f[i-1][j-1]
     * (3) 如果p[j]='*'，他取决于p[j-1]（s=abc, p=ab*，匹配一次）和p[j-2]（s=ac, p=ab*，匹配0次）的匹配情况
     * 1. 若s[i]=p[j-1]，f[i][j]=f[i-1][j](当前s[i]和p[j-1]匹配，看上一次有没有匹配成功的, 如s=abb,p=ab*，i=2,j=2) || f[i][j-2](忽略b*这个组合，不进行匹配，如s=acb,p=acbb*,i=2,j=4)
     * 2. 若s[i]!=p[j-1]，忽略b*这个组合，f[i][j]=f[i][j-2]
     * 4. 综合起来，有如下几种情况：
     * (1) 如果p[j]!='*':
     * 1. matches(s[i],p[j]) -> f[i][j]=f[i-1][j-1]
     * 2. !matches(s[i],p[j]) -> f[i][j]=false
     * (2) 如果p[j]='*':
     * 1. matches(s[i],p[j-1]) -> f[i][j]=f[i-1][j] || f[i][j-2]
     * 2. !matches(s[i],p[j-1]) -> f[i][j]=f[i][j-2]
     * 5. 对于matches方法，主要是判断两个字符是否匹配
     */
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] f = new boolean[sLen + 1][pLen + 1];
        // 字符串从1开始，空字符串之间是匹配的
        f[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                // 虽然让字符串从1开始，但是相对位置不变，只是取字符的时候需要-1
                if (p.charAt(j - 1) == '*') {
                    // 一定是a*，不可能是*a，所以不会越界
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i - 1][j] || f[i][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[sLen][pLen];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
