package leetcode.rank.year2022.october16;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2022/10/16 10:41
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        System.out.println(new Ex3().sumOfNumberAndReverse(10));
    }
    public boolean sumOfNumberAndReverse(int num) {
        if (num == 0) {
            return true;
        }
        Set<Integer> set = new HashSet<>();
        int start = 0;
        while (true) {
            if (start >= num) {
                break;
            }
            if (set.contains(start)) {
                start++;
                continue;
            }
            int reverse = reverse(start);
            if (reverse + start == num) {
                return true;
            }
            set.add(start);
            set.add(reverse);
            start++;
        }
        return false;
    }

    private int reverse(int num) {
        int ans = 0;
        while (num != 0) {
            int mod = num % 10;
            ans = ans * 10 + mod;
            num /= 10;
        }
        return ans;
    }
}
