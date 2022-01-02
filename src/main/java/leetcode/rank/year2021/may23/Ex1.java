package leetcode.rank.year2021.may23;

/**
 * @author yuzhang
 * @date 2021/5/23 上午10:27
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.checkZeroOnes("111000"));
    }

    public boolean checkZeroOnes(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int count0 = 0, count1 = 0;
        int len = -1;
        char tmp = ' ';
        for (char chr : s.toCharArray()) {
            if (chr == tmp) {
                len++;
            } else if (chr == '0') {
                count1 = Math.max(count1, len);
                len = 1;
                tmp = chr;
            } else if (chr == '1') {
                count0 = Math.max(count0, len);
                len = 1;
                tmp = chr;
            }
        }
        if (tmp == '1') {
            count1 = Math.max(count1, len);
        } else {
            count0 = Math.max(count0, len);
        }
        return count1 > count0;
    }
}
