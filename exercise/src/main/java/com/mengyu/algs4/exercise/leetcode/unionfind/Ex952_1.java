package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex952_1 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7, 4, 12, 21, 39};
        Ex952_1 ex952_1 = new Ex952_1();
        System.out.println(ex952_1.largestComponentSize(nums));
    }

    private int[] roots;
    private int[] weights;
    private int weight;

    public int largestComponentSize(int[] nums) {
        init(nums.length);
        // 质因数 -> 拥有这个质因数的nums下标
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        boolean hasPrimeFactorExceptItSelf = false;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            hasPrimeFactorExceptItSelf = false;
            int primeFactor = 2;
            while (primeFactor * primeFactor <= num) {
                if (num % primeFactor == 0) {
                    while (num % primeFactor == 0) {
                        num /= primeFactor;
                    }
                    map.computeIfAbsent(primeFactor, key -> new LinkedList<>()).add(i);
                    hasPrimeFactorExceptItSelf = true;
                }
                primeFactor++;
            }
            if (num > 1 || !hasPrimeFactorExceptItSelf) {
                map.computeIfAbsent(num, key -> new LinkedList<>()).add(i);
            }
        }
        for (Map.Entry<Integer, LinkedList<Integer>> entry : map.entrySet()) {
            LinkedList<Integer> list = entry.getValue();
            int point1 = list.poll();
            while (!list.isEmpty()) {
                connect(point1, list.poll());
            }
        }
        return weight;
    }

    private int greatestCommonDivisor(int a, int b) {
        int mod = 0;
        do {
            mod = a % b;
            a = b;
            b = mod;
        } while (b != 0);
        return a;
    }

    private void connect(int point1, int point2) {
        int root1 = find(point1);
        int root2 = find(point2);
        if (root1 == root2) {
            return;
        }
        if (weights[root1] > weights[root2]) {
            weights[root1] += weights[root2];
            roots[root2] = root1;
            weight = Math.max(weight, weights[root1]);
        } else {
            weights[root2] += weights[root1];
            roots[root1] = root2;
            weight = Math.max(weight, weights[root2]);
        }
    }

    private int find(int point) {
        while (point != roots[point]) {
            roots[point] = roots[roots[point]];
            point = roots[point];
        }
        return point;
    }

    private void init(int len) {
        roots = new int[len];
        weights = new int[len];
        for (int i = 0; i < len; i++) {
            roots[i] = i;
            weights[i] = 1;
        }
        weight = 1;
    }
}
