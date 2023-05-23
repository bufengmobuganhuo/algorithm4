package com.mengyu.algs4.exercise.leetcode.rank.year2022.apr3;

import java.util.*;

/**
 * @author yuzhang
 * @date 2022/4/3 11:14 AM
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        char[] keys = {'a', 'b', 'c', 'z'};
        String[] values = {"aa", "bb", "cc", "zz"};
        String[] dictionary = {"aa", "aaa", "aaaa", "aaaaa", "aaaaaaa"};
        Ex4 ex4 = new Ex4(keys, values, dictionary);
        System.out.println(ex4.decrypt("aaaaaaaaaaaaaa"));
        System.out.println(ex4.decrypt("aaaa"));
        System.out.println(ex4.decrypt("eizfeiam"));
        System.out.println(ex4.decrypt("eizfeiam"));
    }

    private String[] keyMap;

    private Map<String, Integer> encryptCntMap;

    public Ex4(char[] keys, String[] values, String[] dictionary) {
        this.keyMap = new String[26];
        for (int i = 0; i < keys.length; i++) {
            keyMap[keys[i] - 'a'] = values[i];
        }
        this.encryptCntMap = new HashMap<>();
        for (String dic : dictionary) {
            String e = encrypt(dic);
            encryptCntMap.put(e, encryptCntMap.getOrDefault(e, 0) + 1);
        }
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (char chr : word1.toCharArray()) {
            sb.append(keyMap[chr-'a']);
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return encryptCntMap.getOrDefault(word2, 0);
    }
}
