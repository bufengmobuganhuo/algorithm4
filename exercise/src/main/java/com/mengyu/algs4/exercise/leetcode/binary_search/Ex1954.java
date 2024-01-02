package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex1954 {

    public static void main(String[] args) {
        System.out.println(new Ex1954().minimumPerimeter(1000000000));
    }
    public long minimumPerimeter(long neededApples) {
        long n = 1;
        while (getApples(n) < neededApples) {
            n++;
        }
        return n * 8;
    }

    private long getApples(long n) {
        return 2 * n * (n + 1) * (2 * n + 1);
    }

}
