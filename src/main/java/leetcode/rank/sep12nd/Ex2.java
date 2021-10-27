package leetcode.rank.sep12nd;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/9/12 上午10:37
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[][] rectangles = {
                {4, 8},
                {3, 6},
                {10, 20},
                {15, 30}
        };
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.interchangeableRectangles(rectangles));
    }

    public long interchangeableRectangles(int[][] rectangles) {
        Map<String, Integer> map = new HashMap<>();
        for (int[] rec : rectangles) {
            int div = gcd(rec[0], rec[1]);
            String key = (rec[0] / div) + "/" + (rec[1] / div);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        long count = 0;
        for (int x : map.values()) {
            count += 1L * x * (x - 1) / 2;
        }
        return count;
    }

    private int gcd(int a, int b) {
        int mod = 0;
        do {
            mod = a % b;
            a = b;
            b = mod;
        } while (b != 0);
        return a;
    }
}
