package leetcode.rank.year2022.apr10;

/**
 * @author yuzhang
 * @date 2022/4/10 10:27 AM
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        System.out.println(new Ex2().minimizeResult("247+38"));
    }
    public String minimizeResult(String expression) {
        int midIdx = expression.indexOf('+');
        int ans = Integer.MAX_VALUE;
        String res = "";
        for (int i = 0; i < midIdx; i++) {
            int multi1 = i == 0 ? 1 : Integer.parseInt(expression.substring(0, i));
            int add1 = Integer.parseInt(expression.substring(i, midIdx));
            for (int j = expression.length() - 1; j > midIdx; j--) {
                int add2 = Integer.parseInt(expression.substring(midIdx + 1, j + 1));
                int multi2 = j == expression.length() - 1 ? 1 : Integer.parseInt(expression.substring(j+1));
                if (ans > multi1 * multi2 * (add1 + add2)) {
                    ans = multi1 * multi2 * (add1 + add2);
                    res = (i == 0 ? "" : multi1) + "(" + add1 + "+" + add2 + ")" + (j == expression.length() - 1 ? "" :
                            multi2);
                }
            }
        }
        return res;
    }
}
