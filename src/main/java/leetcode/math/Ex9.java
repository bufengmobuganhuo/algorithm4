package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex9 {
    public boolean isPalindrome(int x) {
        StringBuilder sb = new StringBuilder(x);
        return sb.toString().equals(sb.reverse().toString());
    }
}
