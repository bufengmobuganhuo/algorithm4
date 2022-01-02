package leetcode.rank.year2021.junly25;

/**
 * @author yuzhang
 * @date 2021/7/25 上午10:22
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.getLucky("leetcode",2));
    }
    public int getLucky(String s, int k) {
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = 0;
            for (int j = 0; j < s.length(); j++) {
                char chr = s.charAt(j);
                int tmp = Character.isDigit(chr) ? chr - '0' : chr - 'a' + 1;
                res += (tmp / 10) + (tmp % 10);
            }
            s = String.valueOf(res);
        }
        return res;
    }
}
