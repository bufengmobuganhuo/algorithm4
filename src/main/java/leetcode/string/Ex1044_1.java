package leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/12/23 11:08 上午
 * TODO
 */
public class Ex1044_1 {

    public static void main(String[] args) {
        Ex1044_1 ex1044_1 = new Ex1044_1();
        System.out.println(ex1044_1.longestDupSubstring("banana"));
    }

    private long[] h = null;

    private long[] p = null;

    public String longestDupSubstring(String s) {
        int n = s.length();
        int P = 1313131;
        // 索引从1开始
        h = new long[n + 1];
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i < s.length() + 1; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }
        // 二分法，查找长度
        int left = 0, right = n;
        String ans = "";
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String checkRes = check(s, mid);
            if (checkRes.length() > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            ans = checkRes.length() > ans.length() ? checkRes : ans;
        }
        return ans;
    }

    private String check(String s, int len) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long hash = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(hash)) {
                return s.substring(i - 1, j);
            }
            set.add(hash);
        }
        return "";
    }

}
