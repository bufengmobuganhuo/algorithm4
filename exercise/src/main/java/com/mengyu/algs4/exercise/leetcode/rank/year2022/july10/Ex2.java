package com.mengyu.algs4.exercise.leetcode.rank.year2022.july10;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2022/7/10 10:25
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        ex2.addBack(2);
        System.out.println(ex2.popSmallest());
        System.out.println(ex2.popSmallest());
        System.out.println(ex2.popSmallest());
        ex2.addBack(1);
        System.out.println(ex2.popSmallest());
        System.out.println(ex2.popSmallest());
        System.out.println(ex2.popSmallest());

    }
    private int startNum;

    private Set<Integer> missedSet;

    public Ex2() {
        startNum = 1;
        missedSet = new HashSet<>();
    }

    public int popSmallest() {
        int res = startNum;
        startNum++;
        while (missedSet.contains(startNum)) {
            startNum++;
        }
        return res;
    }

    public void addBack(int num) {
        missedSet.remove(num);
        if (num < startNum) {
            if (startNum - num == 1) {
                startNum = num;
            } else {
                for (int i = num + 1; i < startNum; i++) {
                    missedSet.add(i);
                }
                startNum = num;
            }
        }
    }
}
