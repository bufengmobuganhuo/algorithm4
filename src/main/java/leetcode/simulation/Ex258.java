package leetcode.simulation;

/**
 * @author yu zhang
 */
public class Ex258 {
    public int addDigits(int num) {
        String ans = String.valueOf(num);
        while (ans.length() > 1) {
            num = 0;
            for (char chr : ans.toCharArray()) {
                num += (chr - '0');
            }
            ans = String.valueOf(num);
        }
        return num;
    }
}
