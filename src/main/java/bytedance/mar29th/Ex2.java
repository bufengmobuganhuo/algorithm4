package bytedance.mar29th;

import java.math.BigInteger;

/**
 * @author yuzhang
 * @date 2021/3/29 上午8:55
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        String num = "19910011992";
        System.out.println(ex2.isAdditiveNumber(num));
    }

    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <= 2) {
            return false;
        }
        return backtracking(num, 0, null, null);
    }

    private boolean backtracking(String num, int idx, String num1, String num2) {
        if (idx >= num.length() && num1.length() + num2.length() != num.length()) {
            return true;
        } else if (idx >= num.length()) {
            return false;
        }
        if (num1 != null && num2 != null) {
            String sum = new BigInteger(num1).add(new BigInteger(num2)).toString();
            if (sum.length() > num.length() - idx) {
                return false;
            }
            for (int i = idx; i - idx < sum.length(); i++) {
                if (num.charAt(i) != sum.charAt(i - idx)) {
                    return false;
                }
            }
            return backtracking(num, idx + sum.length(), num2, sum);
        }
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(0) == '0' && i > 1) {
                continue;
            }
            num1 = num.substring(0, i);
            for (int j = 1; j + i < num.length(); j++) {
                if (num.charAt(i) == '0' && j > 1) {
                    continue;
                }
                num2 = num.substring(i, j + i);
                if (backtracking(num, i + j, num1, num2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
