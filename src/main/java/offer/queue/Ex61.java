package offer.queue;

import java.util.*;

/**
 * @author yuzhang
 * @date 2022/5/4 12:00
 * TODO
 */
public class Ex61 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
            }
        });
        int m = nums1.length, n = nums2.length;
        // 最小的前几个数一定是：{0, 0},{1, 0},{2, 0}....
        for (int i = 0; i < Math.min(m, k); i++) {
            que.offer(new int[]{i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (k-- > 0 && !que.isEmpty()) {
            int[] idxes = que.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxes[0]]);
            list.add(nums2[idxes[1]]);
            ans.add(list);
            // 对于一个数对{a, b}，下一个有可能可选索引数对为{a+1, b}, {a, b+1}
            // 但是因为上面我们已经选了{i, 0}，则如果选{a+1, b}可能会重复，所以这里只选{a, b+1}
            if (idxes[1] + 1 < n) {
                que.offer(new int[]{idxes[0], idxes[1] + 1});
            }
        }
        return ans;
    }
}
