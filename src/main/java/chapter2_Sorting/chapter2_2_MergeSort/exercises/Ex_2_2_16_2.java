package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import edu.princeton.cs.algs4.In;
import utils.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/10/29 9:59 上午
 * TODO
 */
public class Ex_2_2_16_2 {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(10,15);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static Integer[] tmp;

    public static void sort(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int left = 0, right = 0, mid = 0;
        tmp = new Integer[arr.length];
        while (!(left == 0 && mid == arr.length - 1)) {
            left = 0;
            while (left < arr.length) {
                mid = left + findSortedBlock(arr, left) - 1;
                if (mid == arr.length - 1) {
                    break;
                }
                right = mid + findSortedBlock(arr, mid + 1);
                merge(arr, left, mid, right);
                left = right + 1;
            }
        }
    }

    private static void merge(Integer[] arr, int start, int mid, int end) {
        System.arraycopy(arr, start, tmp, start, end - start + 1);
        int i = start, j = mid + 1;
        for (int k = start; k < end + 1; k++) {
            if (i > mid) {
                arr[k] = tmp[j++];
            } else if (j > end) {
                arr[k] = tmp[i++];
            } else if (arr[i] > arr[j]) {
                arr[k] = tmp[j++];
            } else {
                arr[k] = tmp[i++];
            }
        }
    }

    private static int findSortedBlock(Integer[] arr, int start) {
        int len = 1;
        for (int i = start; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                break;
            }
            len++;
        }
        return len;
    }
}
