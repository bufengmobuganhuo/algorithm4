package com.mengyu.algs4.exercise.lcp.simulation;

/**
 * @author yu zhang
 */
public class Ex50 {
    public int giveGem(int[] gem, int[][] operations) {
        for (int[] operation : operations) {
            int op1 = operation[0], op2 = operation[1];
            int give = gem[op1] / 2;
            gem[op1] -= give;
            gem[op2] += give;
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int g : gem) {
            min = Math.min(g, min);
            max = Math.max(g, max);
        }
        return max - min;
    }
}
