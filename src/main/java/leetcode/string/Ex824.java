package leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex824 {

    private Set<Character> vowelSet = new HashSet<Character>() {
        {
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
            add('A');
            add('E');
            add('I');
            add('O');
            add('U');
        }
    };

    public String toGoatLatin(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder ans = new StringBuilder();
        StringBuilder suffix = new StringBuilder("a");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (vowelSet.contains(word.charAt(0))) {
                ans.append(word);
            } else {
                ans.append(word.substring(1));
                ans.append(word.charAt(0));
            }
            ans.append("ma");
            ans.append(suffix);
            suffix.append("a");
            if (i != words.length - 1) {
                ans.append(" ");
            }
        }
        return ans.toString();
    }
}
