package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex1017 {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        } else if (n == 1) {
            return "1";
        }
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            // 因为余数只可能是0，1，所以这里直接取最后一位就可以
            int remainder = n & 1;
            sb.append(remainder);
            n -= remainder;
            n /= -2;
        }
        return sb.reverse().toString();
    }
}
