package com.mengyu.algs4.knowledge.chapter6_Background;

public interface SuffixArray {
    /**
     * @return 原文本的长度
     */
    int length();

    /**
     * @param i
     * @return 后缀数组中的第i个元素
     */
    String select(int i);

    /**
     * select(i)在原字符串中的索引
     * @param i
     * @return
     */
    int index(int i);

    /**
     * select(i)和select(i-1)的最长公共前缀的长度
     * @param i
     * @return
     */
    int lcp(int i);

    /**
     * 小于键key的数量
     * @param key
     * @return
     */
    int rank(String key);
}
