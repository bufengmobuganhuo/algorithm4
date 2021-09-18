package leetcode.string;

/**
 * @author yu zhang
 */
public class Ex1221 {
    public int balancedStringSplit(String s) {
        int lCount = 0, rCount = 0;
        int res = 0;
        for (char chr : s.toCharArray()) {
            if (chr == 'L') {
                lCount++;
            }else {
                rCount++;
            }
            if (lCount == rCount) {
                res++;
            }
        }
        return res;
    }
}
