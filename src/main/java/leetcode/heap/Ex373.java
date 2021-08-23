package leetcode.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yu zhang
 */
public class Ex373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer[]> que = new PriorityQueue<>((o1, o2) -> o2[0] + o2[1] - o1[0] - o1[1]);
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            for (int j = 0; j < Math.min(k, nums2.length); j++) {
                if (que.size() < k) {
                    que.offer(new Integer[] { nums1[i], nums2[j]});
                } else {
                    Integer[] peek = que.peek();
                    if (peek[0] + peek[1] - nums1[i] - nums2[j] > 0) {
                        que.poll();
                        que.offer(new Integer[]{nums1[i], nums2[j]});
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!que.isEmpty()) {
            Integer[] arr = que.poll();
            List<Integer> list = new ArrayList<>();
            Collections.addAll(list, arr);
            res.add(list);
        }
        return res;
    }
}
