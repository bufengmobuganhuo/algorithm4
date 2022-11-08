package leetcode.rank.year2022.september18;

/**
 * @author yu zhang
 */
public class Ex2 {
    public int longestContinuousSubstring(String s) {
        int leftPtr = 0, rightPtr = 1;
        char nextChr = (char) (s.charAt(leftPtr) + 1);
        int ans = 1;
        while (rightPtr < s.length()) {
            if (nextChr != s.charAt(rightPtr)) {
                ans = Math.max(ans, rightPtr - leftPtr);
                leftPtr = rightPtr;
            }
            nextChr = (char) (s.charAt(rightPtr) + 1);
            rightPtr++;
        }
        ans = Math.max(ans, rightPtr - leftPtr);
        return ans;
    }
}
