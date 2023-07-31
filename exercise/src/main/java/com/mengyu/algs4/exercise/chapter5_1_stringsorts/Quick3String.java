package com.mengyu.algs4.exercise.chapter5_1_stringsorts;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Quick3String {

    public static void main(String[] args) {
        String[] arr = {
                "152",
                "348",
                "539",
                "539",
                "077",
                "483",
                "691",
        };
        Quick3String.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(String[] words) {
        partition(words, 0, words.length - 1, 0);
    }

    private static void partition(String[] words, int lo, int hi, int index) {
        if (lo >= hi) {
            return;
        }
        int lessPtr = lo, greaterPtr = hi;
        int partitionEle = charAt(words[lo], index);
        int i = lo + 1;
        while (i <= greaterPtr) {
            int ele = charAt(words[i], index);
            if (ele < partitionEle) {
                exch(words, lessPtr++, i++);
            } else if (ele > partitionEle) {
                exch(words, greaterPtr--, i);
            } else {
                i++;
            }
        }
        partition(words, lo, lessPtr - 1, index);
        if (partitionEle >= 0) {
            partition(words, lessPtr, greaterPtr, index + 1);
        }
        partition(words, greaterPtr + 1, hi, index);
    }

    private static void exch(String[] words, int i, int j) {
        String tmp = words[i];
        words[i] = words[j];
        words[j] = tmp;
    }

    private static int charAt(String word, int idx) {
        if (idx < word.length()) {
            return word.charAt(idx);
        }
        return -1;
    }
}
