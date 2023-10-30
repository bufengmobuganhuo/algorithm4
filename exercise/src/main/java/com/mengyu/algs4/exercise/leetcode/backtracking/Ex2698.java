package com.mengyu.algs4.exercise.leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex2698 {
    public static void main(String[] args) {
        System.out.println(new Ex2698().punishmentNumber(9));
        System.out.println(new Ex2698().punishmentNumber(10));
    }

    private int[] map = new int[1001];

    public int punishmentNumber(int n) {
        if (map[n] != 0) {
            return map[n];
        }
        int res = 0;
        for (int i = n; i >= 1; i--) {
            if (map[i] != 0) {
                res += map[i];
                break;
            }
            int x = i * i;
            if (find(String.valueOf(x), i, 0, 0)) {
                res+=x;
            }
        }
        map[n] = res;
        return res;
    }



    private boolean find(String pw, int num, int idx, int sum) {
        if (idx >= pw.length()) {
            return sum == num;
        }
        for (int i = 1; i + idx <= pw.length(); i++) {
            int x = Integer.parseInt(pw.substring(idx, i + idx));
            if (x + sum > num) {
                break;
            }
            boolean res = find(pw, num, idx + i, sum + x);
            if (res) {
                return true;
            }
        }
        return false;
    }
}
