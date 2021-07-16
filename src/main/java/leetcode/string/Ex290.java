package leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex290 {
    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat fish";
        Ex290 ex290 = new Ex290();
        System.out.println(ex290.wordPattern(pattern, s));
    }

    public boolean wordPattern(String pattern, String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        String[] map = new String[26];
        Set<String> marked = new HashSet<>();
        String[] words = s.split(" ");
        if (pattern.length() != words.length){
            return false;
        }
        for (int i = 0; i < words.length; i++) {
            char chr = pattern.charAt(i);
            if (map[chr - 'a'] == null && !marked.contains(words[i])) {
                map[chr - 'a'] = words[i];
                marked.add(words[i]);
            } else if (map[chr - 'a'] == null || !map[chr - 'a'].equals(words[i])) {
                return false;
            }
        }
        return true;
    }
}
