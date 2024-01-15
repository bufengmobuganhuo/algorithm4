package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2645 {
    /**
     * 如果知道了最终由多少组"abc"，那用（组数*3 - word.length）就是需要插入的字符数
     * 最终的字符是: abc, abc, abc。可以看到，如果在word中遇到了word[i] <= word[i-1]，则说明他们肯定属于不同的组，从而可以知道组数
     * @param word
     * @return
     */
    public int addMinimum(String word) {
        // 最终一定会有1组
        int cnt = 1, n = word.length();
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) <= word.charAt(i - 1)) {
                cnt++;
            }
        }
        return cnt * 3 - n;
    }
}
