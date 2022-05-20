package leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2063 {
    private static final Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public long countVowels2(String word) {
        long ans = 0;
        for (int i = 0; i < word.length(); i++) {
            // 如果word[i]是元音字符，则子字符串的左断点可以是[0....i]， 右端点可以是[i...n-1]
            if (set.contains(word.charAt(i))) {
                ans += ((long) i + 1) * (word.length() - i);
            }
        }
        return ans;
    }

    // 下述代码可以有空间优化，略
    public long countVowels(String word) {
        // dp[i]: 以word[i]结尾的，把汗原因的子字符串的个数
        int[] dp = new int[word.length()];
        long ans = 0;
        if (set.contains(word.charAt(0))) {
            ans = 1;
            dp[0] = 1;
        }
        for (int i = 1; i < word.length(); i++) {
            if (set.contains(word.charAt(i))) {
                // word[i]是元音，则从word[0....i]都是满足条件的子字符串
                dp[i] = dp[i - 1] + (i + 1);
            } else {
                dp[i] = dp[i - 1];
            }
            ans += dp[i];
        }
        return ans;
    }
}
