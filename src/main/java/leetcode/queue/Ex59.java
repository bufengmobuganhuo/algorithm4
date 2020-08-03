package leetcode.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/7/11 3:19 下午
 * leetcode：剑指offer-59:滑动窗口 的最大值
 * 使用单调队列(k 为滑动窗口长度,len 为数组长度)：
 * 1.使用两个指针，表示两个窗口i范围：[1-k,len-k],j范围：[0,len]
 * 2.在i<0时，说明还没有滑到第一个窗口，则一直入队：
 * 1⃣️ 对于一个待入队元素a，如果队尾元素>a，则a直接入队；如果<a，则从队尾删除元素，直到满足条件为止（从队首 -> 队尾 递减）
 * 3.当i=0时，说明进入到了滑动窗口：
 * 1⃣️ 队列中队首元素就是滑动窗口的最大值
 * 4.当i>0时，说明最起码到了第二个滑动窗口
 * 2⃣️ 如果队首元素同时也是上一个滑动窗口的第一个元素（因为滑动窗口向后移动，第一个元素不在下一个滑动窗口中），则出队
 */
public class Ex59 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        // 队首 -> 队尾：递减
        Deque<Integer> queue = new LinkedList<>();
        int len = nums.length;
        int[] res = new int[len - k + 1];
        for (int i = 1 - k, j = 0; j < len; i++, j++) {
            // 最起码到了第二个滑动窗口，需要删除上一个滑动窗口的第一个元素
            if (i > 0 && queue.peekFirst() == nums[i - 1]) {
                queue.pollFirst();
            }
            //待如对元素只有nums[j]，前面的都已经入过队了
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.pollLast();
            }
            queue.offerLast(nums[j]);
            // i>=0时才算到了滑动窗口
            if (i >= 0) {
                res[i] = queue.peekFirst();
            }
        }
        return res;
    }
}
