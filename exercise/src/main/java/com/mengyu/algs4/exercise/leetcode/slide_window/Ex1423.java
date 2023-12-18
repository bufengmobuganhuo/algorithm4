package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex1423 {

    public static void main(String[] args) {
        int[] cardPoints = {96, 90, 41, 82, 39, 74, 64, 50, 30};
        System.out.println(new Ex1423().maxScore(cardPoints, 8));
    }

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;
        int slideSum = 0;
        for (int i = 0; i < windowSize; i++) {
            slideSum += cardPoints[i];
        }
        int totalSum = slideSum, minSum = slideSum;
        for (int i = windowSize; i < n; i++) {
            slideSum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, slideSum);
            totalSum += cardPoints[i];
        }
        return totalSum - minSum;
    }
}
