package leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1447 {
    public static void main(String[] args) {
        System.out.println(new Ex1447().simplifiedFractions(4));
    }
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        int mod = 0;
        while (b != 0) {
            mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }
}
