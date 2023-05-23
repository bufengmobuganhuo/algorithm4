package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/12/3 上午9:48
 * TODO
 */
public class Ex973 {
    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        Ex973 ex973 = new Ex973();
        System.out.println(Arrays.toString(ex973.kClosest2(points, 2)));
    }

    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue priorityQueue = new PriorityQueue(K);
        for (int i = 0; i < K; i++) {
            priorityQueue.offer(points[i]);
        }
        for (int i = K; i < points.length; i++) {
            if (less(points[i], priorityQueue.peek())) {
                priorityQueue.poll();
                priorityQueue.offer(points[i]);
            }
        }
        int[][] res = new int[K][];
        for (int i = 0; i < K; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }

    class PriorityQueue {
        int[][] pq;
        int N;

        public PriorityQueue(int cap) {
            pq = new int[cap + 1][2];
        }

        public int[] peek() {
            return pq[1];
        }

        public int[] poll() {
            int[] res = pq[1];
            exch(pq, 1, N);
            pq[N--] = null;
            sink(1);
            return res;
        }

        public void offer(int[] point) {
            pq[++N] = point;
            swim(N);
        }

        private void sink(int k) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && less(pq[j], pq[j + 1])) {
                    j++;
                }
                if (less(pq[j], pq[k])) {
                    break;
                }
                exch(pq, k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && less(pq[k / 2], pq[k])) {
                exch(pq, k, k / 2);
                k /= 2;
            }
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        int length = points.length;
        for (int i = (length - 1) / 2 - 1; i >= 0; i--) {
            sink(points, i, length);
        }
        while (length > 0) {
            exch(points, 0, --length);
            sink(points, 0, length);
        }
        int[][] res = new int[K][2];
        if (K >= 0) {
            System.arraycopy(points, 0, res, 0, K);
        }
        return res;
    }

    private void sink(int[][] points, int k, int length) {
        while (2 * k + 1 < length) {
            int j = 2 * k + 1;
            if (j + 1 < length && less(points[j], points[j + 1])) {
                j++;
            }
            if (less(points[j], points[k])) {
                break;
            }
            exch(points, k, j);
            k = j;
        }
    }

    private void exch(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private boolean less(int[] i, int[] j) {
        return Math.sqrt(Math.pow(i[0], 2) + Math.pow(i[1], 2)) <
                Math.sqrt(Math.pow(j[0], 2) + Math.pow(j[1], 2));
    }
}
