package com.mengyu.algs4.exercise.leetcode.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author yu zhang
 */
public class Ex519 {
    public static void main(String[] args) {
        Ex519 ex519 = new Ex519(1, 1);
        ex519.reset();
        System.out.println(Arrays.toString(ex519.flip()));
        System.out.println(Arrays.toString(ex519.flip()));
        System.out.println(Arrays.toString(ex519.flip()));
        System.out.println(Arrays.toString(ex519.flip()));
    }

    private final Map<Integer, Integer> map;

    private final int m, n;

    private int total;

    private final Random random;

    public Ex519(int m, int n) {
        map = new HashMap<>();
        this.m = m;
        this.n = n;
        this.total = m * n;
        this.random = new Random();
    }

    /**
     * 1. 将[i,j]映射成i * n + j
     * 2. 设当前还剩下k个为0的元素，为了避免"排除值为1"的情况，保证数组中[0...k-1]是为0的，剩下位置为1
     * 当翻转了一个位置为x(x<=k-1)的元素后，将位置x的实际映射位置改成k-1（保证了如果下次还是随机出来x,它对应的值还是0）
     * 这样可以保证前k个元素都是0
     */
    public int[] flip() {
        int x = random.nextInt(total);
        total--;
        // 找到实际映射的位置
        int idx = map.getOrDefault(x, x);
        map.put(x, map.getOrDefault(total, total));
        return new int[]{idx / n, idx % n};
    }

    public void reset() {
        total = this.m * this.n;
        map.clear();
    }
}
