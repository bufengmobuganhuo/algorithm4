package leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex984 {
    public static void main(String[] args) {
        System.out.println(new Ex984().strWithout3a3b(4, 2));
    }
    public String strWithout3a3b(int a, int b) {
        StringBuilder ans = new StringBuilder();
        while (a > 0 || b > 0) {
            boolean writeA = false;
            int len = ans.length();
            if (len > 2 && ans.charAt(len - 1) == ans.charAt(len - 2)) {
                if (ans.charAt(len - 1) == 'b') {
                    writeA = true;
                }
            } else {
                if (a >= b) {
                    writeA = true;
                }
            }
            if (writeA) {
                ans.append('a');
                a--;
            } else {
                ans.append('b');
                b--;
            }
        }
        return ans.toString();
    }
}
