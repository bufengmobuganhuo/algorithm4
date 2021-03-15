package chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/3/5 上午10:15
 * TODO
 */
public class Ex_5_3_8_2 {
    private String pattern;
    private int[] next;

    public Ex_5_3_8_2(String pattern) {
        this.pattern = pattern;
        this.next = new int[pattern.length() + 1];
    }

    public int search(String txt) {
        int i = 0, j = 0;
        while (i < txt.length() && j < pattern.length()) {
            if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return i - j;
        }
        return -1;
    }

    public List<Integer> searchAll(String txt) {
        int i = 0, j = 0;
        List<Integer> res = new ArrayList<>();
        while (i < txt.length() && j < pattern.length()) {
            if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    res.add(i - j);
                    j = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return res;
    }

    private void generateNext() {
        int i = 0, j = -1;
        while (i < pattern.length()) {
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
