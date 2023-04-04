package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1638 {

    // 枚举做法
    public int countSubstrings(String s, String t) {
        int ans = 0;
        for (int sStart = 0; sStart < s.length(); sStart++) {
            for (int tStart = 0; tStart < t.length(); tStart++) {
                int diff = 0;
                for (int i = 0; i + sStart < s.length() && i + tStart < t.length(); i++) {
                    diff += s.charAt(i+sStart) == t.charAt(i+tStart) ? 0 : 1;
                    if (diff == 1) {
                        ans++;
                    } else if (diff > 1) {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 设以字符s[i]与字符t[j]为终点且左侧连续相等的最大长度为dpl[i][j]，以字符s[i]与字符t[j]为终点且右侧连续相等的最大长度dpr[i][j]
     * 那么他们能组成的子串个数就是(dpl[i][j]+1)*(dpr[i][j]+1)
     */
    public int countSubstrings2(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dpr = new int[m + 1][n + 1], dpl = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dpl[i + 1][j + 1] = s.charAt(i) == t.charAt(j) ? (dpl[i][j] + 1) : 0;
            }
        }
        for (int i = m - 1; i > -1; i--) {
            for (int j = n - 1; j > -1; j--) {
                dpr[i][j] = s.charAt(i) == t.charAt(j) ? (dpr[i + 1][j + 1] + 1) : 0;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    ans += (dpl[i][j] + 1) * (dpr[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }
}
