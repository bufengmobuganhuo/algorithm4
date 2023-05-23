package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yuzhang
 * @date 2021/3/11 上午9:37
 * TODO
 */
public class Ex838_1 {
    public static void main(String[] args) {
        Ex838_1 ex838_1 = new Ex838_1();
        System.out.println(ex838_1.pushDominoes(".L.R."));
    }
    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() == 0) {
            return "";
        }
        int[] symbolIdx = new int[dominoes.length() + 2];
        char[] symbols = new char[dominoes.length() + 2];
        char[] ans = dominoes.toCharArray();
        symbolIdx[0] = -1;
        symbols[0] = 'L';
        // 按照L或R分段
        int index = 1;
        for (int i = 0; i < dominoes.length(); i++) {
            if ('.' != dominoes.charAt(i)) {
                symbolIdx[index] = i;
                symbols[index++] = dominoes.charAt(i);
            }
        }
        symbolIdx[index] = dominoes.length();
        symbols[index] = 'R';
        for (int i = 0; i < index; i++) {
            int leftPtr = symbolIdx[i], rightPtr = symbolIdx[i + 1];
            char leftSymbol = symbols[i], rightSymbol = symbols[i + 1];
            if (leftSymbol == rightSymbol) {
                for (int j = leftPtr + 1; j < rightPtr; j++) {
                    ans[j] = leftSymbol;
                }
            } else if (leftSymbol > rightSymbol) {
                for (int j = leftPtr + 1; j < rightPtr; j++) {
                    int leftDistance = j - leftPtr;
                    int rightDistance = rightPtr - j;
                    if (leftDistance > rightDistance) {
                        ans[j] = rightSymbol;
                    } else if (leftDistance < rightDistance) {
                        ans[j] = leftSymbol;
                    } else {
                        ans[j] = '.';
                    }
                }
            }
        }
        return String.valueOf(ans);
    }
}
