package leetcode.greedy;

/**
 * @author yuzhang
 * @date 2021/6/7 上午8:10
 * TODO
 */
public class Ex5778 {
    public static void main(String[] args) {
        String s = "111000";
        Ex5778 ex5778 = new Ex5778();
        System.out.println(ex5778.minFlips(s));
    }
    public int minFlips(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder(s);
        int len = sb.length();
        int idx = 0;
        int count = 0;
        while (idx < len) {
            if (idx < sb.length() - 1 && sb.charAt(idx) != sb.charAt(idx + 1)) {
                break;
            }
            sb.append(sb.charAt(idx));
            idx++;
        }
        sb.delete(0, idx);
        char start = sb.charAt(0);
        idx = 1;
        while (idx < len) {
            if (sb.charAt(idx) == start){
                count++;
            }
            start = start == '0' ? '1' : '0';
            idx++;
        }
        return count;
    }
}
