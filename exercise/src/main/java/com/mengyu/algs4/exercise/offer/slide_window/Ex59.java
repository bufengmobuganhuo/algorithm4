package com.mengyu.algs4.exercise.offer.slide_window;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/3/3 上午10:35
 * TODO
 */
public class Ex59 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        Ex59 ex59 = new Ex59();
        System.out.println(Arrays.toString(ex59.maxSlidingWindow(nums, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        Deque<Integer> que = new LinkedList<>();
        int[] res = new int[len - k + 1];
        for (int i = 1 - k, j = 0; j < len; i++, j++) {
            // 如果至少到达了第二个滑动窗口，并且单调队列的最大值是上一个滑动窗口的值，则去掉
            if (i > 0 && nums[i - 1] == que.peekFirst()) {
                que.pollFirst();
            }
            // 单调队列入队
            while (!que.isEmpty() && nums[j] > que.peekLast()) {
                que.pollLast();
            }
            que.offerLast(nums[j]);
            if (i>=0){
                res[i]=que.peekFirst();
            }
        }
        return res;
    }

}
