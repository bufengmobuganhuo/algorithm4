package com.mengyu.algs4.exercise.leetcode.rank.year2022.july31;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2022/7/31 10:29
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums = {1,5,0,3,5};
        System.out.println(new Ex1().minimumOperations(nums));
    }
    public int minimumOperations(int[] nums) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int num : nums) {
            if (num > 0) {
                que.offer(num);
            }
        }
        int count = 0;
        while (!que.isEmpty()) {
            int min = que.poll();
            if (min <= 0) {
                return count;
            }
            List<Integer> list = new ArrayList<>();
            while (!que.isEmpty()) {
                int tmp = que.poll();
                if (tmp - min > 0) {
                    list.add(tmp - min);
                }
            }
            for (int num : list) {
                que.offer(num);
            }
            count++;
        }
        return count;
    }
}
