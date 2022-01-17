package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;


import java.awt.image.ImageProducer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/11/30 上午9:31
 * TODO
 */
public class Ex_5_3_26_BM1 {

    public boolean solution(String s, String t) {
        return s.length() == t.length() && contains(s, t);
    }

    private boolean contains(String txt, String target) {
        Map<Character, Integer> right = generateRight(target);
        int skipStep = 0;
        for (int i = 0; i < txt.length() - target.length(); i += skipStep) {
            skipStep = 0;
            for (int j = target.length() - 1; j >= 0; j--) {
                if (txt.charAt(i + j) != target.charAt(j)) {
                    skipStep = j - right.getOrDefault(txt.charAt(i + j), -1);
                    skipStep = Math.max(skipStep, 1);
                    break;
                }
            }
            if (skipStep == 0) {
                return true;
            }
        }
        return false;
    }

    private Map<Character, Integer> generateRight(String target) {
        Map<Character, Integer> right = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            right.put(target.charAt(i), i);
        }
        return right;
    }
}
