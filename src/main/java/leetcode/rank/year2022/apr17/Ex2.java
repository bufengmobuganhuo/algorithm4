package leetcode.rank.year2022.apr17;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/4/17 10:23 AM
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] tasks = {3, 3, 3, 3};
        System.out.println(new Ex2().minimumRounds(tasks));
    }

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        int count = 0;
        for (int val : map.values()) {
            int x = 0;
            while (val % 3 != 0 && val > 0) {
                x++;
                val = val - 2;
            }
            if (val < 0) {
                count = -1;
                break;
            }
            count += x + (val / 3);
        }
        return count;
    }
}
