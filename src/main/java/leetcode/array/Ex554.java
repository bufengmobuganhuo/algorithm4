package leetcode.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex554 {
    public static void main(String[] args) {

    }
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int max = 0;
        for (List<Integer> layer : wall) {
            int sum = 0;
            for (int i = 0; i < layer.size() - 1; i++) {
                sum += layer.get(i);
                int count = countMap.getOrDefault(sum, 0);
                countMap.put(sum, count + 1);
                max = Math.max(max, count + 1);
            }
        }
        return wall.size() - max;
    }
}
