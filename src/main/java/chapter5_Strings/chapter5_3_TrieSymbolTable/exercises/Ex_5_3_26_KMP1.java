package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

/**
 * @author yuzhang
 * @date 2020/11/30 上午9:01
 * TODO
 */
public class Ex_5_3_26_KMP1 {

    public boolean solution(String s, String t) {
        return s.length() == t.length() && contains(s, t);
    }

    private boolean contains(String pattern, String txt) {
        int[] next = generateNext(pattern);
        int i = 0, j = 0;
        while (i < txt.length() && j < pattern.length()) {
            if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == pattern.length();
    }

    private int[] generateNext(String pattern) {
        int[] next = new int[pattern.length()];
        int i = 0, j = -1;
        next[0] = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
