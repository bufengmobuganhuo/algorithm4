package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;

import com.mengyu.algs4.utils.ArrayUtil;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/2/11 11:23
 * 练习2.2.15：自底向上的有序队列归并排序
 */
public class EX_2_2_15 {
    public static void main(String[] args) {
        Comparable[] arr = ArrayUtil.createInt(15, 20);
        Queue<Queue<Comparable>> res = solution(arr);
        Queue<Comparable> queue = res.poll();
        for (Comparable item : queue) {
            System.out.print(item + " ");
        }
    }

    public static Queue<Queue<Comparable>> solution(Comparable[] arr) {
        Queue<Queue<Comparable>> queues = createQueue(arr);
        while (queues.size() != 1) {
            Queue<Comparable> queue1 = queues.poll();
            Queue<Comparable> queue2 = queues.poll();
            Queue<Comparable> mergeRes = EX_2_2_14.solution(queue1, queue2);
            queues.add(mergeRes);
        }
        return queues;
    }

    private static Queue<Queue<Comparable>> createQueue(Comparable[] arr) {
        Queue<Queue<Comparable>> res = new ArrayDeque<>(arr.length);
        for (Comparable item : arr) {
            Queue<Comparable> queue = new ArrayDeque<>(1);
            queue.add(item);
            res.add(queue);
        }
        return res;
    }
}
