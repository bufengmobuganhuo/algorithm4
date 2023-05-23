package com.mengyu.algs4.exercise.leetcode.rank.year2021.august8;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2021/8/8 上午10:24
 * TODO
 */
public class Ex2 {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (int) (Math.floor(o2 / 2) - Math.floor(o1 / 2));
            }
        });
        int sum = 0;
        for (int pile : piles) {
            que.offer(pile);
            sum += pile;
        }
        for (int i = 0; i < k; i++) {
            int pile = que.poll();
            if (pile <= 0) {
                break;
            }
            int remove = (int) Math.floor(pile / 2);
            sum -= remove;
            que.offer(pile - remove);
        }
        return sum;
    }
}
