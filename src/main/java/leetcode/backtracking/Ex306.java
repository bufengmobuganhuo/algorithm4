package leetcode.backtracking;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/31 2:39 下午
 * TODO
 */
public class Ex306 {
    public static void main(String[] args) {
        Ex306 ex306 = new Ex306();
        System.out.println(ex306.isAdditiveNumber("101"));
    }

    private boolean isAdditiveNumber;

    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        backtracking(num, 0, new ArrayList<>());
        return isAdditiveNumber;
    }

    private void backtracking(String num, int start, List<BigDecimal> track) {
        // 只有实际选过的才算，否则直接输入"0"也可以通过
        if (start == num.length() && track.size() > 2) {
            isAdditiveNumber = true;
            return;
        }
        for (int i = 1; i <= num.length() - start; i++) {
            String num3Str = num.substring(start, i + start);
            // 去除掉"01"开头的情况
            if (num3Str.startsWith("0") && num3Str.length() > 1) {
                continue;
            }
            if (track.size() > 1) {
                BigDecimal num2 = track.get(track.size() - 1);
                BigDecimal num1 = track.get(track.size() - 2);
                BigDecimal num3 = new BigDecimal(num3Str);
                if (!num1.add(num2).equals(num3)) {
                    continue;
                }
            }
            track.add(new BigDecimal(num3Str));
            backtracking(num, start + i, track);
            track.remove(track.size() - 1);
        }
    }
}
