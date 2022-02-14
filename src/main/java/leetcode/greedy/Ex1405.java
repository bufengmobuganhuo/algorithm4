package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1405 {
    public static void main(String[] args) {
        System.out.println(new Ex1405().longestDiverseString(1, 1, 7));
    }
    public String longestDiverseString(int a, int b, int c) {
        int[][] pairs = {{a, 0}, {b, 1}, {c, 2}};
        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean hasNext = false;
            Arrays.sort(pairs, (o1, o2) -> o2[1] - o1[1]);
            for (int[] pair : pairs) {
                if (pair[1] <= 0) {
                    continue;
                }
                char chr = (char) (pair[0] + 'a');
                int len = sb.length();
                if (len >= 2 && sb.charAt(len - 2) == chr && sb.charAt(len - 1) == chr) {
                    continue;
                }
                hasNext = true;
                sb.append(chr);
                pair[1]--;
                break;
            }
            if (!hasNext) {
                break;
            }
        }
        return sb.toString();
    }
}
