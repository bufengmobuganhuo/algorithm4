package com.mengyu.algs4.exercise.leetcode.rank.year2022.may29;

/**
 * @author yuzhang
 * @date 2022/5/29 10:26
 * TODO
 */
public class Ex2 {
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.startsWith("$")) {
                continue;
            }
            int end = 1;
            StringBuilder sb = new StringBuilder();
            while (end < word.length() && (word.charAt(end) == '.' || Character.isDigit(word.charAt(end)))) {
                sb.append(word.charAt(end));
                end++;
            }
            if (end != word.length() || end == 1) {
                continue;
            }
            String price = String.format("$%.2f", Double.parseDouble(sb.toString()) * (1 - (double)discount / 100));
            words[i] = price;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            ans.append(words[i]);
            if (i != words.length - 1) {
                ans.append(" ");
            }
        }
        return ans.toString();
    }
}
