package com.mengyu.algs4.knowledge.chapter6_Background;

/**
 * @author yuzhang
 * @date 2020/6/19 3:58 下午
 * 最长重复子字符串
 */
public class LRS {
    public static void main(String[] args) {
        String text = "aacaagtttacaagc";
        SuffixArray suffixArray = new SimpleSuffixArray(text);
        String lrs = "";
        for (int i = 1; i < text.length(); i++) {
            int length = suffixArray.lcp(i);
            if (length > lrs.length()) {
                lrs = suffixArray.select(i).substring(0, length);
            }
        }
        System.out.println(lrs);
    }
}
