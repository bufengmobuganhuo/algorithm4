package com.mengyu.algs4.exercise.offer.greedy;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex45 {
    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(new Ex45().minNumber(nums));
    }
    public String minNumber(int[] nums) {
        PriorityQueue<String> que = new PriorityQueue<>((o1, o2) -> {
            String str1 = o1 + o2;
            String str2 = o2 + o1;
            return str1.compareTo(str2);
        });
        for (int num : nums) {
            que.offer(String.valueOf(num));
        }
        StringBuilder ans = new StringBuilder();
        while (!que.isEmpty()) {
            ans.append(que.poll());
        }
        return ans.toString();
    }
}
