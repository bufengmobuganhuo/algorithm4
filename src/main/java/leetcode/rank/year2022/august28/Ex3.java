package leetcode.rank.year2022.august28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/8/28 10:42
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        String[] garbage = {"G","P","GP","GG"};
        int[] travel = {2,4,3};
        System.out.println(new Ex3().garbageCollection(garbage, travel));
    }

    private static final String GARBAGE = "MPG";

    public int garbageCollection(String[] garbage, int[] travel) {
        int len = garbage.length;
        Map<Character, List<Integer>> fastPath = new HashMap<>();
        Map<Character, Integer>[] mapped = new Map[len];
        int[] travelPreSum = new int[len];
        for (int i = 0; i < len; i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (char chr : garbage[i].toCharArray()) {
                List<Integer> paths = fastPath.computeIfAbsent(chr, key -> new ArrayList<>());
                if (paths.size() == 0 || paths.get(paths.size() - 1) != i) {
                    paths.add(i);
                }
                map.put(chr, map.getOrDefault(chr, 0) + 1);
            }
            mapped[i] = map;
            travelPreSum[i] = i > 0 ? travelPreSum[i-1] + travel[i-1] : 0;
        }
        int count = 0;
        for (Map.Entry<Character, List<Integer>> entry : fastPath.entrySet()) {
            char targetGarbage = entry.getKey();
            int lastIdx = -1;
            for (int idx : entry.getValue()) {
                count += lastIdx == -1 ? travelPreSum[idx] : travelPreSum[idx] - travelPreSum[lastIdx];
                count += mapped[idx].get(targetGarbage);
                lastIdx = idx;
            }
        }
        return count;
    }
}
