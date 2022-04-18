package leetcode.math;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex806 {
    public static void main(String[] args) {
        int[] widths = {4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

        System.out.println(Arrays.toString(new Ex806().numberOfLines(widths, "bbbcccdddaaa")));
    }

    public int[] numberOfLines(int[] widths, String s) {
        int row = 0, count = 0;
        for (char chr : s.toCharArray()) {
            if (count + widths[chr - 'a'] > 100) {
                row++;
                count = widths[chr - 'a'];
            } else {
                count += widths[chr - 'a'];
            }
        }
        return new int[]{row + (count > 0 ? 1 : 0), count};
    }
}
