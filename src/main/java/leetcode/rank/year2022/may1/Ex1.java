package leetcode.rank.year2022.may1;

/**
 * @author yuzhang
 * @date 2022/5/1 10:20
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().removeDigit("5858159465657234213377184113991581487169858335236259134471179998688444555663388559559761734444929515", '3'));
    }
    public String removeDigit(String number, char digit) {
        String ans = "";
        StringBuilder sb = new StringBuilder(number);
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == digit && sb.deleteCharAt(i).toString().compareTo(ans) > 0) {
                ans = sb.toString();
            }
            sb = new StringBuilder(number);
        }
        return ans;
    }
}
