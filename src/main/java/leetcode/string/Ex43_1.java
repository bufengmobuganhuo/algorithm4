package leetcode.string;

/**
 * @author yuzhang
 * @date 2020/11/6 8:34 上午
 * TODO
 */
public class Ex43_1 {
    public static void main(String[] args) {
        String num1 = "9236";
        String num2 = "635";
        Ex43_1 ex43 = new Ex43_1();
        System.out.println(ex43.multiply(num1, num2));
    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return null;
        }
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] arrAns = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                arrAns[i + j + 1] += x * y;
            }
        }
        for (int i = len1 + len2 - 1; i > 0; i--) {
            int multi = arrAns[i];
            if (multi > 9) {
                arrAns[i] = multi % 10;
                arrAns[i - 1] += multi / 10;
            }
        }
        StringBuilder res = new StringBuilder();
        int idx = arrAns[0] == 0 ? 1 : 0;
        while (idx < arrAns.length) {
            res.append(arrAns[idx++]);
        }
        return res.toString();
    }
}
