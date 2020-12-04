package leetcode.greedy;

import sun.tools.jconsole.HTMLPane;

import java.awt.*;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/12/2 上午9:45
 * TODO
 */
public class Ex621 {
    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        Ex621 ex621 = new Ex621();
        System.out.println(ex621.leastInterval2(tasks, 2));
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        MaxPriorityQueue priorityQueue = new MaxPriorityQueue(26);
        for (char task : tasks) {
            map[task - 'A']++;
        }
        for (int freq : map) {
            if (freq > 0) {
                priorityQueue.offer(freq);
            }
        }
        int time = 0;
        while (!priorityQueue.isEmpty()) {
            int i = 0;
            List<Integer> tmp = new ArrayList<>();
            while (i <= n) {
                if (!priorityQueue.isEmpty()) {
                    if (priorityQueue.peek() > 1) {
                        tmp.add(priorityQueue.poll() - 1);
                    } else {
                        priorityQueue.poll();
                    }
                }
                time++;
                // 如果为空，说明没有任务可以排，都是空闲，也就不需要再继续排了
                if (priorityQueue.isEmpty() && tmp.size() == 0) {
                    break;
                }
                i++;
            }
            for (int freq : tmp) {
                priorityQueue.offer(freq);
            }
        }
        return time;
    }

    static class MaxPriorityQueue {
        int[] pq;
        int N;

        public MaxPriorityQueue(int cap) {
            pq = new int[cap + 1];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int peek() {
            return pq[1];
        }

        public int poll() {
            int res = pq[1];
            exch(1, N--);
            sink(1);
            return res;
        }

        public void offer(int value) {
            pq[++N] = value;
            swim(N);
        }

        private void sink(int k) {
            while (2 * k <= N) {
                int j = k * 2;
                if (j < N && pq[j] < pq[j + 1]) {
                    j++;
                }
                if (pq[k] >= pq[j]) {
                    break;
                }
                exch(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && pq[k] > pq[k / 2]) {
                exch(k, k / 2);
                k /= 2;
            }
        }

        private void exch(int i, int j) {
            int tmp = pq[i];
            pq[i] = pq[j];
            pq[j] = tmp;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char task : tasks) {
            map[task - 'A']++;
        }
        int time = 0;
        // 按照出现频率由大到小排序
        quickSort(map, 0, map.length - 1);
        while (map[0] > 0) {
            int i = 0;
            // 每轮分配n+1个任务，从而保证每个任务每轮被分配一次，保证每个相同任务之间有n个冷却
            while (i <= n) {
                // 如果出现次数最大的分配完，则停止此次分配
                if (map[0] == 0) {
                    break;
                }
                // 每次分配时优先分配出现次数较大的任务
                if (i < 26 && map[i] > 0) {
                    map[i]--;
                }
                // 如果任务耗尽（map[i]==0），则令此次空闲
                time++;
                i++;
            }
            // 重新排序，保证出现频率由大到小
            quickSort(map, 0, map.length - 1);
        }
        return time;
    }

    private void quickSort(int[] tasks, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partitionIdx = partition(tasks, lo, hi);
        quickSort(tasks, lo, partitionIdx - 1);
        quickSort(tasks, partitionIdx + 1, hi);
    }

    private int partition(int[] tasks, int lo, int hi) {
        int left = lo, right = hi + 1;
        int partitionEle = tasks[left];
        while (true) {
            while (tasks[++left] > partitionEle) {
                if (left >= hi) {
                    break;
                }
            }
            while (tasks[--right] <= partitionEle) {
                if (right <= lo) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            exch(tasks, left, right);
        }
        exch(tasks, lo, right);
        return right;
    }

    private void exch(int[] tasks, int i, int j) {
        int tmp = tasks[i];
        tasks[i] = tasks[j];
        tasks[j] = tmp;
    }
}
