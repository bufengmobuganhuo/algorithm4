package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

/**
 * @author yuzhang
 * @date 2021/3/5 上午9:41
 * TODO
 */
public class KmpTry {
    private String pattern;
    private int[] next;

    public KmpTry(String pattern) {
        this.pattern = pattern;
        next = new int[pattern.length()];
    }

    public int search(String txt) {
        int i = 0, j = 0;
        while (i<txt.length()&&j<pattern.length()){
            if (txt.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if (j==pattern.length()-1){
            return i-j;
        }
        return -1;
    }

    private void generateNext() {
        int i = 0, j = -1;
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
