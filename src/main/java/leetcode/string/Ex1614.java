package leetcode.string;

/**
 * @author yu zhang
 */
public class Ex1614 {
    public int maxDepth(String s) {
        int ans = 0;
        int leftBracket = 0;
        int rightBracket = 0;
        for (char chr : s.toCharArray()) {
            if (chr == '(') {
                leftBracket++;
            } else if (chr == ')') {
                rightBracket++;
            }
            ans = Math.max(ans, leftBracket - rightBracket);
        }
        return ans;
    }
}
