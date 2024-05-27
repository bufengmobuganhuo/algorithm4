package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1535 {

    public static void main(String[] args) {
        System.out.println(new Ex1535().getWinner2(new int[]{1,25,35,42,68,70}, 2));
    }

    // 因为数组中的数各不相同，如果 k <= arr.length，则一定可以在遍历完数组后选出获胜者
    public int getWinner2(int[] arr, int k) {
        int pre = Math.max(arr[0], arr[1]);
        if (k == 1) {
            return pre;
        }
        int cnt = 1;
        for (int i = 2; i < arr.length; i++) {
            if (pre > arr[i]) {
                cnt++;
            } else {
                pre = arr[i];
                cnt = 1;
            }
            if (cnt == k) {
                return pre;
            }
        }
        return pre;
    }

    public int getWinner(int[] arr, int k) {
        if (arr.length <= k) {
            int max = 0;
            for (int num : arr) {
                max = Math.max(max, num);
            }
            return max;
        }
        int win = -1, winCnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }
        int ptr = 1;
        while (true) {
            if (list.get(0) > list.get(ptr)) {
                if (win == list.get(0)) {
                    winCnt++;
                } else {
                    win = list.get(0);
                    winCnt = 1;
                }
                if (winCnt >= k) {
                    return win;
                }
                list.add(list.get(ptr));
                ptr++;
            } else {
                win = list.get(ptr);
                winCnt = 1;
                if (winCnt >= k) {
                    return win;
                }
                list.add(list.get(0));
                list.set(0, list.get(ptr));
                ptr++;
            }
        }
    }
}
