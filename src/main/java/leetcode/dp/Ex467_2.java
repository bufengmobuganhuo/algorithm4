package leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex467_2 {
    public int findSubstringInWraproundString(String p) {
        // 26个英文字母中，以a, b, c...结尾的子串的最长长度
        int[] dp = new int[26];
        int cnt = 0;
        for (int i = 0; i < p.length(); i++) {
            // 如果连续，则截止到p[i]，连续子串的长度是cnt
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                cnt++;
            } else {
                cnt = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], cnt);
        }
        return Arrays.stream(dp).sum();
    }

}
