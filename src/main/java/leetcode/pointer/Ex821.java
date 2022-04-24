package leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex821 {
    public int[] shortestToChar(String s, char c) {
        int[] ans = new int[s.length()];
        int n = s.length();
        // 从左开始遍历，找到s[i]左边的c
        for (int i = 0, idx = -n; i < n; i++) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }

        // 从右开始遍历，找到s[i]右边的c
        for (int i = n - 1, idx = 2*n; i >= 0; i--) {
            if (s.charAt(i) == c) {
                idx= i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }
}
