package leetcode.rank.year2022.december04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/12/4 10:03
 * TODO
 */
public class Ex2 {
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        long sum = Arrays.stream(skill).sum();
        if (sum % (n / 2) != 0) {
            return -1;
        }
        long teamSum = sum / (n / 2);
        Map<Integer, Integer> map = new HashMap<>();
        long ans = 0;
        for (int point : skill) {
            int other = (int) (teamSum - point);
            int count = map.getOrDefault(other, 0);
            if (count == 0) {
                map.put(point, map.getOrDefault(point, 0) + 1);
            } else {
                ans += ((long) point * other);
                if (count == 1) {
                    map.remove(other);
                } else {
                    map.put(other, count - 1);
                }
            }
        }
        if (map.size() > 0) {
            return -1;
        }
        return ans;
    }
}
