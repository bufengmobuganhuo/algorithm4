package leetcode.string;

/**
 * @author yu zhang
 */
public class Ex551 {
    public boolean checkRecord(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int aCount = 0, lCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                aCount++;
                lCount = 0;
            } else if (s.charAt(i) == 'L' && (i == 0 || s.charAt(i - 1) == 'L' || lCount == 0)) {
                lCount++;
            }else {
                lCount = 0;
            }
            if (aCount >= 2 || lCount >= 3) {
                return false;
            }
        }
        return true;
    }
}
