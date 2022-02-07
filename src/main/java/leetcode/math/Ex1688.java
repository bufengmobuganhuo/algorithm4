package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex1688 {
    public int numberOfMatches(int n) {
        int counter = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                counter += n / 2;
                n /= 2;
            } else {
                counter += (n - 1) / 2;
                n = (n - 1) / 2 + 1;
            }
        }
        return counter;
    }
}
