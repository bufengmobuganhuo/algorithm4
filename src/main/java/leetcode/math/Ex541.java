package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex541 {
    public static void main(String[] args) {
        Ex541 ex541 = new Ex541();
        System.out.println(ex541.reverseStr("abcdefg", 2));
    }
    public String reverseStr(String s, int k) {
        int step = 2 * k;
        int start = 0;
        char[] chrs = s.toCharArray();
        while (start < chrs.length) {
            reverse(chrs, start, start + k - 1);
            start += step;
        }
        return new String(chrs);
    }

    private void reverse(char[] chrs, int start, int end) {
        end = Math.min(end, chrs.length - 1);
        while (start < end) {
            char tmp = chrs[start];
            chrs[start] = chrs[end];
            chrs[end] = tmp;
            start++;
            end--;
        }
    }
}
