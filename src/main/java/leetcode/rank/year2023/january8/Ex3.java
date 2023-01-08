package leetcode.rank.year2023.january8;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2023/1/8 10:35
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        System.out.println(new Ex3().isItPossible("wilfuzpxqserkdcvbgajtyhon", "rlmyvwvucqxsjodbelmgjkabnxegihuwats"));
    }
    public boolean isItPossible(String word1, String word2) {
        Map<Character, Integer> word1Map = new HashMap<>();
        for (char chr : word1.toCharArray()) {
            word1Map.put(chr, word1Map.getOrDefault(chr, 0) + 1);
        }
        Map<Character, Integer> word2Map = new HashMap<>();
        for (char chr : word2.toCharArray()) {
            word2Map.put(chr, word2Map.getOrDefault(chr, 0) + 1);
        }
        if (word1Map.size() == word2Map.size()) {
            if (word1Map.size() == 1) {
                int count1 = word1Map.get(word1.charAt(0));
                int count2 = word2Map.get(word2.charAt(0));
                if (count1 == count2) {
                    return true;
                } else if (count1 == 1) {
                    return word1.charAt(0) == word2.charAt(0);
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
        if (word1Map.size() < word2Map.size()) {
            return filter(word2Map, word1Map);
        } else {
            return filter(word1Map, word2Map);
        }
    }

    private boolean filter(Map<Character, Integer> word1Map, Map<Character, Integer> word2Map) {
        for (char chr1 : word1Map.keySet()) {
            for (char chr2 : word2Map.keySet()) {
                if (chr1 == chr2 ) {
                    continue;
                }
                int size1 = word1Map.size();
                if (word1Map.getOrDefault(chr2, 0) == 0) {
                    size1++;
                }
                if (word1Map.get(chr1) == 1) {
                    size1--;
                }
                int size2 = word2Map.size();
                if (word2Map.getOrDefault(chr1, 0) == 0) {
                    size2++;
                }
                if (word2Map.get(chr2) == 1) {
                    size2--;
                }
                if (size1 == size2) {
                    return true;
                }
            }
        }
        return false;
    }
}
