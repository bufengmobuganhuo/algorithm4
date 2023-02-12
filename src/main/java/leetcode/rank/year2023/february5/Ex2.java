package leetcode.rank.year2023.february5;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2023/2/5 10:15
 * TODO
 */
public class Ex2 {
    private Set<Character> set = new HashSet<>();

    public Ex2() {
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int len = words.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length() - 1))) {
                prefixSum[i + 1] += prefixSum[i] + 1;
            } else {
                prefixSum[i + 1] = prefixSum[i];
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            ans[i] = prefixSum[query[1] + 1] - prefixSum[query[0]];
        }
        return ans;
    }
}
