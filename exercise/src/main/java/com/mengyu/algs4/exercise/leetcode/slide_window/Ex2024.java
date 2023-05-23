package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex2024 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxConsecutiveAnswers(answerKey, k, 'T'), maxConsecutiveAnswers(answerKey, k, 'F'));
    }

    private int maxConsecutiveAnswers(String answerKey, int k, char chr) {
        int sum = 0, ans = 0, len = answerKey.length();
        for (int left = 0, right = 0; right < len; right++) {
            sum += (answerKey.charAt(right) == chr ? 0 : 1);
            while (sum > k) {
                sum -= (answerKey.charAt(left) == chr ? 0 : 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
