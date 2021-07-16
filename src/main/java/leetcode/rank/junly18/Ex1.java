package leetcode.rank.junly18;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/7/18 上午10:31
 * TODO
 */
public class Ex1 {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> set = new HashSet<>();
        for (char chr : brokenLetters.toCharArray()) {
            set.add(chr);
        }
        String[] words = text.split(" ");
        int count = words.length;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (char chr : word.toCharArray()) {
                if (set.contains(chr)){
                    count--;
                    break;
                }
            }
        }
        return count;
    }
}
