package leetcode.backtracking;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2021/3/11 上午11:12
 * TODO
 */
public class Ex306_1 {
    public static void main(String[] args) {
        Ex306_1 ex306_1 = new Ex306_1();
        System.out.println(ex306_1.isAdditiveNumber("111"));
    }


    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) {
            return false;
        }
        return backtracking(num, 0, BigDecimal.ZERO, BigDecimal.ZERO, 0);
    }

    private boolean backtracking(String num, int startIdx, BigDecimal num1, BigDecimal num2, int count) {
        if (startIdx >= num.length()) {
            return count > 2;
        }
        for (int i = 1; i < num.length() - startIdx + 1; i++) {
            if (num.charAt(startIdx) == '0' && i > 1) {
                continue;
            }
            BigDecimal nextNum = new BigDecimal(num.substring(startIdx, i + startIdx));
            if (count >= 2) {
                BigDecimal sum = num1.add(num2);
                if (sum.compareTo(nextNum) > 0) {
                    continue;
                } else if (sum.compareTo(nextNum) < 0) {
                    break;
                }
            }
            if (backtracking(num, startIdx + i, num2, nextNum, count + 1)) {
                return true;
            }
        }
        return false;
    }
}
