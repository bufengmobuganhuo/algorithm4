package com.mengyu.algs4.exercise.chapter2_sort;

/**
 * @author yu zhang
 */
public class PriorityQueue<T extends Comparable<T>> {
    private T[] que;

    private int N;

    public PriorityQueue(int size) {
        que = (T[]) new Comparable[size + 1];
    }

    public T delMin() {
        if (N <= 0) {
            return null;
        }
        T min = que[1];
        exch(1, N);
        que[N--] = null;
        sink(1);
        return min;
    }

    public void insert(T t) {
        if (N >= que.length) {
            return;
        }
        que[++N] = t;
        swim(N);
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j + 1 <= N && less(que[j], que[j + 1])) {
                j++;
            }
            if (less(que[k], que[j])) {
                break;
            }
            exch(j, k);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && less(que[k], que[k / 2])) {
            exch(k, k / 2);
        }
    }

    private void exch(int i, int j) {
        T tmp = que[i];
        que[i] = que[j];
        que[j] = tmp;
    }

    private boolean less(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }
}
