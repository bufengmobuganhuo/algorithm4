package leetcode.math;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author yuzhang
 * @date 2020/12/7 下午4:08
 * TODO
 */
public class Ex1680 {
    public static void main(String[] args) {
        Ex1680 ex1680 = new Ex1680();
        System.out.println(ex1680.concatenatedBinary(3));
    }

    public int concatenatedBinary(int n) {
        String str = recursive(new StringBuilder(), 1, n);
        return convert(str);
    }

    private int convert(String str) {
        long mod = (long) (Math.pow(10, 9) + 7);
        int len = str.length();
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                continue;
            }
            res += (Math.pow(2, len - i - 1) % mod);
        }
        return (int) (res%mod);
    }

    private String recursive(StringBuilder stringBuilder, int n, int target) {
        stringBuilder.append(Long.toBinaryString(n));
        if (n == target) {
            return stringBuilder.toString();
        }
        return recursive(stringBuilder, n + 1, target);
    }
}
