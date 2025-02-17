package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex838_4 {
    public static void main(String[] args) {
        System.out.println(new Ex838_4().pushDominoes("RR.L"));
    }
    public String pushDominoes(String dominoes) {
        StringBuilder sb = new StringBuilder(dominoes);
        sb.insert(0, 'L');
        sb.append('R');
        int n = sb.length();
        int l = 0, r = 1;
        while (r < n) {
            while (r < n && sb.charAt(r) == '.') {
                r++;
            }
            char left = sb.charAt(l), right = sb.charAt(r);
            if (left == 'L' && right == 'R') {
                l = r;
            } else if (left == 'R' && right == 'R') {
                while (l < r) {
                    sb.setCharAt(l++, 'R');
                }
            } else if (left == 'L' && right == 'L') {
                while (l < r) {
                    sb.setCharAt(l++, 'L');
                }
            } else {
                int len = (r - l + 1);
                int mid = l + len / 2;
                while (l < r) {
                    if (l < mid) {
                        sb.setCharAt(l, 'R');
                    } else if (l > mid) {
                        sb.setCharAt(l, 'L');
                    } else if (len % 2 != 1){
                        sb.setCharAt(mid, 'L');
                    }
                    l++;
                }
                l = r;
            }
            r = l + 1;
        }
        return sb.substring(1, sb.length() - 1);
    }
}
