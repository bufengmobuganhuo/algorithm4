package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import chapter5_Strings.chapter5_3_TrieSymbolTable.KMP;

/**
 * @author yuzhang
 * @date 2020/8/18 11:10 上午
 * TODO
 */
public class Kmp {
    public static void main(String[] args) {
        String target = "ababababca";
        String pattern = "abababca";
        Kmp kmp = new Kmp();
        System.out.println(kmp.search(target, pattern));
    }

    private int[] next;

    public int search(String target, String pattern) {
        generate(pattern);
        int i = 0, j = 0;
        while (i < target.length() && j < pattern.length()) {
            if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private void generate(String pattern) {
        next = new int[pattern.length()];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }
}
