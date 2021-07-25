package leetcode.queue;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex239 {

    /**
     * 1. 可以维护一个由大到小的单调队列，那么队首就有可能是滑动窗口的最大值，这需要根据下标来判断
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> que = new LinkedList<>();
        // 先放k个
        for (int i = 0; i < k; i++) {
            while (!que.isEmpty() && nums[que.peekLast()] <= nums[i]) {
                que.pollLast();
            }
            que.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[que.peekFirst()];
        for (int i = k; i < n; i++) {
            while (!que.isEmpty() && nums[que.peekLast()] <= nums[i]) {
                que.pollLast();
            }
            que.offerLast(i);
            // 看队首元素是否在滑动窗口中
            while (que.peekFirst() < i - k + 1) {
                que.pollFirst();
            }
            ans[i - k + 1] = nums[que.peekFirst()];
        }
        return ans;
    }

    /**
     * 1. 使用一个优先队列可以很方便的获取最大值（类似topK问题）
     * 2. 但是在获取最大值时需要考虑两个问题：
     * （1）当前最大值是否在滑动窗口中：需要在队列中存入下标，poll时判断是否符合条件
     * （2）对于大小相同的数字，需要按照出现顺序排序：再放入优先队列时，后出现的在堆顶，因为后出现的更可能在当前窗口中
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // (index)
        PriorityQueue<Integer> que =
            new PriorityQueue<>((o1, o2) -> nums[o1] != nums[o2] ? nums[o2] - nums[o1] : o2 - o1);
        // 先放k个
        for (int i = 0; i < k; i++) {
            que.offer(i);
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = nums[que.peek()];
        for (int i = k; i < n; i++) {
            que.offer(i);
            while (que.peek() < i - k + 1) {
                que.poll();
            }
            ans[i - k + 1] = nums[que.peek()];
        }
        return ans;
    }
}
