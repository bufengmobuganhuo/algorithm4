package com.mengyu.algs4.exercise.leetcode.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex264_1 {

    /**
     * 因为丑数都是通过2，3，5相乘得到的，故三个指针分别表示如下含义
     * ptr2: 指向还未得到机会与2相乘的丑数，其前一个表示已经得到了与2相乘的机会
     * 其他同理
     *
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        int[] uglies = new int[n + 1];
        uglies[0] = 1;
        int ptr2 = 0, ptr3 = 0, ptr5 = 0;
        for (int i = 1; i < n; i++) {
            int ugly = Math.min(uglies[ptr2] * 2, Math.min(uglies[ptr3] * 3, uglies[ptr5] * 5));
            uglies[i] = ugly;
            if (ugly == uglies[ptr2] * 2) {
                ptr2++;
            }
            if (ugly == uglies[ptr3] * 3) {
                ptr3++;
            }
            if (ugly == uglies[ptr5] * 5) {
                ptr5++;
            }
        }
        return uglies[n - 1];
    }

    // 使用优先队列
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> que = new PriorityQueue<>();
        // 用于去重
        Set<Long> seen = new HashSet<>();
        long res = 1;
        que.offer(res);
        seen.add(1L);
        int[] primes = {2, 3, 5};
        for (int i = 0; i < n; i++) {
            res = que.poll();
            // 堆顶的一定是最小值，则分别乘，放入堆中，就是下一个丑数
            for (int prime : primes) {
                if (seen.add(prime * res)) {
                    que.offer(prime * res);
                }
            }
        }
        return (int) res;
    }
}
