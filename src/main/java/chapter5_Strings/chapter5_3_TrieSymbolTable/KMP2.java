package chapter5_Strings.chapter5_3_TrieSymbolTable;

/**
 * @author yuzhang
 * @date 2020/11/27 上午9:05
 * TODO
 */
public class KMP2 {
    public static void main(String[] args) {
        String target = "ababababca";
        String pattern = "abababca";
        KMP2 kmp = new KMP2(pattern);
        System.out.println(kmp.search(target));
    }
    private final String pattern;
    private int[] next;

    public KMP2(String pattern) {
        this.pattern = pattern;
        generateNext();
    }

    public String search(String txt) {
        int i = 0, j = -1;
        while (i < txt.length() && j < pattern.length()) {
            if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return txt.substring(i - j, i - j + pattern.length());
        }
        return null;
    }

    private void generateNext() {
        next = new int[pattern.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
    }
}
