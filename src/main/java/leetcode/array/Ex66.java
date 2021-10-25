package leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex66 {
    public static void main(String[] args) {
        Ex66 ex66 = new Ex66();
        int[] digits = {9,9};
        System.out.println(Arrays.toString(ex66.plusOne(digits)));
    }
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (digits[len - 1] < 9) {
            digits[len - 1]++;
            return digits;
        }
        LinkedList<Integer> list = new LinkedList<>();
        list.offerLast(0);
        int bit = 1;
        for (int i = len - 2; i >= 0; i--) {
            int sum = digits[i] + bit;
            if (sum < 10) {
                list.offerFirst(sum);
                bit = 0;
            } else {
                list.offerFirst(0);
                bit = 1;
            }
        }
        if (bit == 1) {
            list.offerFirst(1);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
