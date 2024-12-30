package com.mengyu.algs4.exercise.leetcode.heap;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author yu zhang
 */
public class Ex855 {

    public static void main(String[] args) {
        Ex855 ex855 = new Ex855(8);
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());

        ex855.leave(0);
        ex855.leave(7);

        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());
        System.out.println(ex855.seat());

        ex855.leave(0);
        ex855.leave(4);

        System.out.println(ex855.seat());
    }

    private int n;

    private TreeSet<Integer> seats;

    private PriorityQueue<int[]> que;

    public Ex855(int n) {
        this.n = n;
        this.seats = new TreeSet<>();
        this.que = new PriorityQueue<>((o1, o2) -> {
            int left1 = o1[0] == Integer.MIN_VALUE ? -1 * o1[1] - 2: o1[0];
            int right1 = o1[1] == Integer.MAX_VALUE ? 2 * n - 2 - o1[0]: o1[1];
            int distance1 = (right1 - left1) / 2;
            int left2 = o2[0] == Integer.MIN_VALUE ? -1 * o2[1] - 2: o2[0];
            int right2 = o2[1] == Integer.MAX_VALUE ? 2 * n - 2 - o2[0]: o2[1];
            int distance2 = (right2 - left2) / 2;
            if (distance1 != distance2) {
                return distance2 - distance1;
            }
            if (o1[0] == Integer.MIN_VALUE) {
                return -1;
            }
            if (o2[0] == Integer.MIN_VALUE) {
                return 1;
            }
            return left1 - left2;
        });
    }

    public int seat() {
        if (que.isEmpty()) {
            int[] range = new int[]{0, Integer.MAX_VALUE};
            que.offer(range);
            seats.add(0);
            return 0;
        }
        int[] range = que.poll();
        if (range[0] == Integer.MIN_VALUE) {
            range[0] = 0;
            que.offer(range);
            seats.add(0);
            return 0;
        } else if (range[1] == Integer.MAX_VALUE) {
            range[1] = n - 1;
            que.offer(range);
            seats.add(n - 1);
            return n - 1;
        } else {
            int mid = range[0] + (range[1] - range[0]) / 2;
            int[] left = new int[]{range[0], mid};
            int[] right = new int[]{mid, range[1]};
            que.offer(left);
            que.offer(right);
            seats.add(mid);
            return mid;
        }
    }

    public void leave(int p) {
        seats.remove(p);
        Integer left = seats.floor(p);
        Integer right = seats.ceiling(p);
        if (left == null) {
            left = Integer.MIN_VALUE;
        }
        if (right == null) {
            right = Integer.MAX_VALUE;
        }
        Integer finalLeft = left;
        Integer finalRight = right;
        que.removeIf(ints -> (ints[0] == finalLeft && ints[1] == p) || (ints[0] == p && ints[1] == finalRight));
        if (left != Integer.MIN_VALUE || right != Integer.MAX_VALUE) {
            que.offer(new int[]{left, right});
        }
    }
}
