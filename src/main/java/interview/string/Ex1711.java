package interview.string;

import huawei.Ex1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1711 {
    public static void main(String[] args) {
    }
    public int findClosest(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                idx1 = i;
                if (idx2 != -1) {
                    distance = Math.min(distance, idx1 - idx2);
                }
            } else if (word.equals(word2)) {
                idx2 = i;
                if (idx1 != -1) {
                    distance = Math.min(distance, idx2 - idx1);
                }
            }
        }
        return distance;
    }
}
