package leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1424 {
    public static void main(String[] args) {

    }

    public int[] findDiagonalOrder2(List<List<Integer>> nums) {
        int total = 0, col = 0;
        for (List<Integer> list : nums) {
            // 总共有几个数
            total += list.size();
            // 最长列数
            col = Math.max(col, list.size());
        }
        // 最多有 nums.size + col个索引和
        List<Integer>[] arr = new List[nums.size() + col];
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                // 索引和
                int idx = i + j;
                if (arr[idx] == null) {
                    arr[idx] = new ArrayList<>();
                }
                arr[idx].add(nums.get(i).get(j));
            }
        }
        int idx = 0;
        int[] ans = new int[total];
        for (List<Integer> list : arr) {
            if (list == null) {
                continue;
            }
            // 反着来，就是答案
            for (int i = list.size() - 1; i >= 0; i--) {
                ans[idx++] = list.get(i);
            }
        }
        return ans;
    }

    /**
     * 1. 对角线上的元素索引(i+j)是一样的，并且从左边到右边对角线的和递增
     * 2. 对于同一条对角线上的索引(i, j)，j是递增的
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
            if (a[0] + a[1] == b[0] + b[1]) {
                return a[1] - b[1];
            } else {
                return a[0] + a[1] - b[0] - b[1];
            }
        });
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                que.offer(new int[]{i, j});
            }
        }
        int[] ans = new int[que.size()];
        int idx = 0;
        while (!que.isEmpty()) {
            int[] position = que.poll();
            ans[idx++] = nums.get(position[0]).get(position[1]);
        }
        return ans;
    }
}
