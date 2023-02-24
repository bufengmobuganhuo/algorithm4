package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2347 {
    public String bestHand(int[] ranks, char[] suits) {
        Map<Character, Integer> suitsMap = new HashMap<>();
        Map<Integer, Integer> ranksMap = new HashMap<>();
        for (int i = 0; i < ranks.length; i++) {
            suitsMap.put(suits[i], suitsMap.getOrDefault(suits[i], 0) + 1);
            ranksMap.put(ranks[i], ranksMap.getOrDefault(ranks[i], 0) + 1);
        }
        if (suitsMap.size() == 1) {
            return "Flush";
        }
        if (ranksMap.size() == 5) {
            return "High Card";
        }
        for (Map.Entry<Integer, Integer> entry : ranksMap.entrySet()) {
            if (entry.getValue() >= 3) {
                return "Three of a Kind";
            }
        }
        return "Pair";
    }
}
