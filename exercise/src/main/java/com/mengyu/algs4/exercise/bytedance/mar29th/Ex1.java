package com.mengyu.algs4.exercise.bytedance.mar29th;

/**
 * @author yuzhang
 * @date 2021/3/29 上午8:47
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] arr = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.peakIndexInMountainArray(arr));
    }

    public int peakIndexInMountainArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int leftPtr = 0, rightPtr = arr.length - 1;
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            int mid = arr[midPtr];
            int left = midPtr > 0 ? arr[midPtr - 1] : Integer.MIN_VALUE;
            int right = midPtr < arr.length - 1 ? arr[midPtr + 1] : Integer.MIN_VALUE;
            if (mid > left && mid > right) {
                return midPtr;
            } else if (mid < left && mid > right) {
                rightPtr = midPtr;
            } else {
                leftPtr = midPtr;
            }
        }
        return -1;
    }
}
