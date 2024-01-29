package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex2166 {

    public static void main(String[] args) {
        Ex2166 ex2166 = new Ex2166(2);
        ex2166.flip();
        ex2166.unfix(1);
        System.out.println(ex2166.all());
    }

    private int cnt = 0;

    private int[] bits;

    private int n;

    private boolean flipped;

    public Ex2166(int size) {
        n = size;
        bits = new int[n];
    }

    public void fix(int idx) {
        if (bits[idx] == 1 && flipped) {
            bits[idx] = 0;
            cnt++;
        } else if (bits[idx] == 0 && !flipped) {
            bits[idx] = 1;
            cnt++;
        }
    }

    public void unfix(int idx) {
        if (bits[idx] == 0 && flipped) {
            bits[idx] = 1;
            cnt--;
        } else if (bits[idx] == 1 && !flipped) {
            bits[idx] = 0;
            cnt--;
        }
    }

    public void flip() {
        cnt = n - cnt;
        flipped = !flipped;
    }

    public boolean all() {
        return cnt == n;
    }

    public boolean one() {
        return cnt > 0;
    }

    public int count() {
        return cnt;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (flipped) {
                sb.append(bits[i] == 1 ? 0 : 1);
            } else {
                sb.append(bits[i]);
            }
        }
        return sb.toString();
    }
}
