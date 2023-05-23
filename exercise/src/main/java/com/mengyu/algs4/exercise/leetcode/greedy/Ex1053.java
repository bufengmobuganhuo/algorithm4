package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1053 {
    public static void main(String[] args) {
        int[] arr= {1, 9, 4, 6, 7};
        System.out.println(Arrays.toString(new Ex1053().prevPermOpt1(arr)));
    }
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 2; i > -1; i--) {
            // 是倒序遍历，那如果arr[i] > arr[i+1]，才说明能在arr[i]的右侧找到一个比它小的
            if (arr[i] > arr[i+1]) {
                int j = n - 1;
                // 找到下标最小的j，才是所有符合条件中最大的
                while (arr[j] >= arr[i] || arr[j] == arr[j - 1]) {
                    j--;
                }
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                break;
            }
        }
        return arr;
    }
}
