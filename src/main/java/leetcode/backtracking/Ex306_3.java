package leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex306_3 {
    public boolean isAdditiveNumber(String num) {
        return backTrack(num, 0, 0, 0, 0);
    }

    private boolean backTrack(String num, int idx, long pre, long sum, int count) {
        if (idx >= num.length()) {
            return count > 2;
        }
        long cur = 0;
        for (int i = idx; i < num.length(); i++) {
            if (num.charAt(idx) == '0' && i > idx) {
                continue;
            }
            cur = cur * 10 + num.charAt(i) - '0';
            if (count >= 2) {
                if (cur < sum) {
                    continue;
                } else if (cur > sum) {
                    break;
                }
            }
            if (backTrack(num, i + 1, cur, cur + pre, count + 1)) {
                return true;
            }
        }
        return false;
    }
}
