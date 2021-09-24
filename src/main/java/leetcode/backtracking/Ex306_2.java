package leetcode.backtracking;

import java.math.BigDecimal;

/**
 * @author yu zhang
 */
public class Ex306_2 {
    public static void main(String[] args) {
        Ex306_2 ex306_2 = new Ex306_2();
        System.out.println(ex306_2.isAdditiveNumber("1023"));
        System.out.println(BigDecimal.valueOf(112).toString());
    }
    private String num;
    private int len;
    public boolean isAdditiveNumber(String num) {
        this.num = num;
        this.len = num.length();
        return backTrack(0, 0, 0, 0);
    }

    /**
     *
     * @param idx 从哪里开始截取
     * @param sum 表示的是前两个元素相加的和
     * @param pre 上一个元素
     * @param count 表示数列的长度
     * @return
     */
    public boolean backTrack(int idx, long sum, long pre, int count){
        if (idx >= len) {
            return count > 2;
        }
        long current = 0;
        for (int i = idx; i < len; i++) {
            // 过滤掉 01 的情况
            if (num.charAt(idx) == '0' && i > idx) {
                continue;
            }
            // 字符串转数字，每次都是累加，这样提升了速度
            current = current * 10 + num.charAt(i) - '0';
            // 如果已经有2个元素，则可以比较是否满足累加数列的要求
            if (count >= 2) {
                // 第三个数比前两个数的和小
                if (current < sum) {
                    continue;
                } else if (current > sum) {
                    break;
                }
            }
            if (backTrack(i + 1, pre + current, current, count + 1)) {
                return true;
            }
        }

        return false;
    }
}
