package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex2047 {
    public static void main(String[] args) {

        System.out.println(new Ex2047().countValidWords(". ! 7hk  al6 l! aon49esj35la k3 7u2tkh  7i9y5  !jyylhppd et v- h!ogsouv 5"));
    }
    public int countValidWords(String sentence) {
        int count = 0, punctuationCounter = 0, hyphenCounter = 0;
        boolean hasNumeric = false;
        for (int i = 0; i < sentence.length(); i++) {
            char chr = sentence.charAt(i);
            if (chr == ' ') {
                count = count + (check(sentence, punctuationCounter, hyphenCounter, hasNumeric, i) ? 1 : 0);
                punctuationCounter = 0;
                hyphenCounter = 0;
                hasNumeric = false;
            } else if (hasNumeric) {
            } else if (chr == '!' || chr == '.' || chr == ',') {
                punctuationCounter++;
            } else if (chr == '-') {
                hyphenCounter++;
                if (i == 0 || i == sentence.length() - 1 || !Character.isLowerCase(sentence.charAt(i - 1)) || !Character.isLowerCase(sentence.charAt(i + 1))) {
                    hasNumeric = true;
                    continue;
                }
            } else if (Character.isDigit(chr)) {
                hasNumeric = true;
            }
        }
        return check(sentence, punctuationCounter, hyphenCounter, hasNumeric, sentence.length()) ? count + 1 : count;
    }

    private boolean check(String sentence, int punctuationCounter, int hyphenCounter, boolean hasNumeric, int i) {
        if (i == 0 || hasNumeric || sentence.charAt(i - 1) == ' ') {
            return false;
        }
        if (hyphenCounter > 1) {
            return false;
        }
        if (punctuationCounter > 1) {
            return false;
        }
        if (punctuationCounter == 1) {
            return sentence.charAt(i - 1) == '!' || sentence.charAt(i - 1) == '.' || sentence.charAt(i - 1) == ',';
        }
        return true;
    }
}
