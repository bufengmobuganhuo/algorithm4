package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1146 {

    public static void main(String[] args) {
        Ex1146 ex1146 = new Ex1146(1);
        ex1146.snap();
        ex1146.snap();
        ex1146.set(0, 4);
        ex1146.snap();
        System.out.println(ex1146.get(0, 1));
        ex1146.set(0, 12);
        System.out.println(ex1146.get(0, 1));
        ex1146.snap();
        ex1146.get(0, 3);
    }

    private final List<int[]>[] arr;

    private int snapId;

    public Ex1146(int length) {
        arr = new List[length];
        snapId = 0;
        for (int i = 0; i < length; i++) {
            arr[i] = new ArrayList<>();
        }
    }

    public void set(int index, int val) {
        List<int[]> list = arr[index];
        int size = list.size();
        if (size > 0 && list.get(size - 1)[0] == snapId) {
            list.get(size - 1)[1] = val;
        } else {
            list.add(new int[]{snapId, val});
        }
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        int idx = floor(arr[index], snap_id);
        if (idx == -1) {
            return 0;
        }
        return arr[index].get(idx)[1];
    }

    private int floor(List<int[]> list, int target) {
        int l = 0, r = list.size() - 1;
        int floor = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (list.get(m)[0] == target) {
                return m;
            } else if (list.get(m)[0] < target) {
                floor = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return floor;
    }
}
