package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex3258 {

    public static void main(String[] args) {
        System.out.println(new Ex3258().countKConstraintSubstrings("10101", 1));
    }

    public int countKConstraintSubstrings(String s, int k) {
        int ans = 0;
        int[] cnt = new int[2];
        for (int i = 0, j = 0; j < s.length(); j++) {
            cnt[s.charAt(j) - '0']++;
            while (cnt[0] > k && cnt[1] > k) {
                cnt[s.charAt(i) - '0']--;
                i++;
            }
            ans += j - i + 1;
        }
        return ans;
    }
}
