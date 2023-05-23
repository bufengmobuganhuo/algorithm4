package com.mengyu.algs4.exercise.leetcode.rank.year2022.june12;

/**
 * @author yuzhang
 * @date 2022/6/12 10:22
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {

    }
    public double calculateTax(int[][] brackets, int income) {
        int len = brackets.length;
        double ans = 0;
        if (income <= brackets[0][0]) {
            return income * ((double)brackets[0][1] / 100);
        }
        int lastUpper = 0;
        for (int i = 0; i < len; i++) {
            int upper = brackets[i][0];
            if (upper <= income) {
                ans += (upper - lastUpper) * ((double)brackets[i][1] / 100);
                lastUpper = upper;
            } else if (income > lastUpper) {
                ans += (income - lastUpper) * ((double)brackets[i][1]/ 100);
                break;
            }
        }
        return ans;
    }
}
