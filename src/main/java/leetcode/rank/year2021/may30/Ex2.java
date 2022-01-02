package leetcode.rank.year2021.may30;

/**
 * @author yuzhang
 * @date 2021/5/30 上午10:35
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.maxValue("99",9));
    }
    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder(n);
        int idx = 0;
        if (sb.charAt(0) == '-') {
            idx++;
            while (idx < sb.length() && sb.charAt(idx) - '0' <= x) {
                idx++;
            }
            sb.insert(idx, x);
        } else {
            while (idx < sb.length() && sb.charAt(idx) - '0' >= x){
                idx++;
            }
            sb.insert(idx, x);
        }
        return sb.toString();
    }
}
