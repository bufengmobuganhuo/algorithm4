package com.mengyu.algs4.exercise.leetcode.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex373_1 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer[]> que = new PriorityQueue<>((o1, o2) -> o2[0] + o2[1] - o1[0] - o1[1]);
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            for (int j = 0; j < Math.min(k, nums2.length); j++) {
                if (que.size() < k) {
                    que.offer(new Integer[]{nums1[i], nums2[i]});
                } else {
                    Integer[] peek = que.peek();
                    if (peek[0] + peek[1] > nums1[i] + nums2[i]) {
                        que.poll();
                        que.offer(new Integer[]{nums1[i], nums2[i]});
                    }
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (!que.isEmpty()) {
            Integer[] arr = que.poll();
            List<Integer> list = new ArrayList<>();
            Collections.addAll(list, arr);
            ans.add(list);
        }
        return ans;
    }
}
