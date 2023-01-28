package leetcode.rank.year2023.january28;

/**
 * @author yu zhang
 */
public class Ex1 {
    public int alternateDigitSum(int n) {
        String num = String.valueOf(n);
        int ans = 0, multi = 1;
        for (char chr : num.toCharArray()) {
            int x = chr - '0';
            ans += (x * multi);
            multi = -1 * multi;
        }
        return ans;
    }
}
