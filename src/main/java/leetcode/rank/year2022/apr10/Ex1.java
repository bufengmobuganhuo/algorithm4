package leetcode.rank.year2022.apr10;

/**
 * @author yuzhang
 * @date 2022/4/10 10:27 AM
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().largestInteger(1234));
    }
    public int largestInteger(int num) {
        StringBuilder sb = new StringBuilder(Integer.toString(num));
        for (int i = 0; i < sb.length(); i++) {
            for (int j = i + 1; j < sb.length(); j++) {
                if (sb.charAt(i) < sb.charAt(j) && (((sb.charAt(i) - '0') % 2) == ((sb.charAt(j) - '0') % 2))) {
                    char chr = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(j));
                    sb.setCharAt(j, chr);
                }
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
