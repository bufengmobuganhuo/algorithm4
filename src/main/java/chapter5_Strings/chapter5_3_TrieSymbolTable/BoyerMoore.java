package chapter5_Strings.chapter5_3_TrieSymbolTable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/6/10 11:50
 * TODO
 */
public class BoyerMoore {
    public static void main(String[] args) {
        String target = "ababababca";
        String pattern = "abababca";
        BoyerMoore boyerMoore = new BoyerMoore(pattern);
        int start = boyerMoore.search(target);
        if (start < target.length()) {
            System.out.println(target.substring(start, start + pattern.length()));
        }
    }

    private Map<Character, Integer> right;
    private String pattern;

    public BoyerMoore(String pattern) {
        this.pattern = pattern;
        this.right = new HashMap<>(pattern.length());
        //记录模式字符串中，每个字符最右出现位置
        for (int i = 0; i < pattern.length(); i++) {
            right.put(pattern.charAt(i), i);
        }
    }

    public int search(String target) {
        //i要向右移动的步数
        int skipStep;
        for (int i = 0; i <= target.length() - pattern.length(); i += skipStep) {
            skipStep = 0;
            for (int j = pattern.length() - 1; j >= 0; j--) {
                if (target.charAt(i + j) != pattern.charAt(j)) {
                    skipStep = j - right.getOrDefault(target.charAt(i + j), -1);
                    //如果无法让i增大，则直接向右移动一步
                    skipStep = Math.max(skipStep, 1);
                    break;
                }
            }
            //全部匹配
            if (skipStep == 0) {
                return i;
            }
        }
        //没有匹配
        return target.length();
    }
}
