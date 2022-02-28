package leetcode.simulation;

/**
 * @author yu zhang
 */
public class Ex537 {
    public static void main(String[] args) {
        System.out.println(new Ex537().complexNumberMultiply("1+-1i", "11+1i"));
    }

    public String complexNumberMultiply(String num1, String num2) {
        for (int idx1 = 0, idx2 = 0;;) {
            if (num1.charAt(idx1) == '+' && num2.charAt(idx2) == '+') {
                int realPart1 = Integer.parseInt(num1.substring(0, idx1));
                int realPart2 = Integer.parseInt(num2.substring(0, idx2));
                int visualPart1 = Integer.parseInt(num1.substring(idx1 + 1, num1.length() - 1));
                int visualPart2 = Integer.parseInt(num2.substring(idx2 + 1, num2.length() - 1));
                return (realPart1 * realPart2 - visualPart1 * visualPart2) + "+" + (realPart1 * visualPart2 + realPart2 * visualPart1) + "i";
            }
            if (num1.charAt(idx1) != '+') {
                idx1++;
            }
            if (num2.charAt(idx2) != '+') {
                idx2++;
            }
        }
    }
}
