package leetcode.string;

/**
 * @author yu zhang
 */
public class Ex796 {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        s += s;
        return s.contains(goal);
    }
}
