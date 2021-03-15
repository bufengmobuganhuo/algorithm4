package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/5/23 上午10:28
 * TODO
 */
public class Ex5765 {
    public static void main(String[] args) {
        Ex5765 ex5765 = new Ex5765();
        System.out.println(ex5765.canReach("01101110", 2, 3));
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        int len = s.length();
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            if (!dp[i]) {
                continue;
            }
            for (int j = i + minJump; j < i + maxJump + 1 && j < len && !dp[j]; j++) {
                dp[j] = s.charAt(j) == '0';
            }
        }
        return dp[len - 1];
    }

    private boolean canReach(String s, int i) {
        return s.charAt(i) == '0';
    }
}
