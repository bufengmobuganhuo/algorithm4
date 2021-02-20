package chapter2_Sorting;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/2/4 上午8:51
 * TODO
 */
public class Sort {
    public static void main(String[] args) {
        int[] nums = {3,5,4,2,1,0,6,7,9,8};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 5, 4, 2, 1, 0, 6, 7, 9, 8};
        shellSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 5, 4, 2, 1, 0, 6, 7, 9, 8};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 5, 4, 2, 1, 0, 6, 7, 9, 8};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 5, 4, 2, 1, 0, 6, 7, 9, 8};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
    private static void heapSort(int[] arr) {
        check(arr);
        int len = arr.length;
        for (int i = (len - 1) / 2 - 1; i >= 0; i--) {
            sink(arr, len, i);
        }
        while (len > 0) {
            exch(arr, 0, --len);
            sink(arr, len, 0);
        }
    }

    private static void sink(int[] arr, int len, int k) {
        while (2 * k + 1 < len) {
            int j = 2 * k + 1;
            if (j + 1 < len && arr[j + 1] > arr[j]) {
                j++;
            }
            if (arr[k] > arr[j]) {
                break;
            }
            exch(arr, k, j);
            k = j;
        }
    }

    private static void shellSort(int[] arr) {
        check(arr);
        int len = arr.length;
        int h = 1;
        while (h < len / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    exch(arr, j, j - h);
                }
            }
            h /= 3;
        }
    }

    private static void selectionSort(int[] arr) {
        check(arr);
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            exch(arr, i, minIdx);
        }
    }

    private static void insertionSort(int[] arr) {
        check(arr);
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                exch(arr, j, j - 1);
            }
        }
    }

    private static void bubbleSort(int[] arr) {
        check(arr);
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    exch(arr, j, j + 1);
                }
            }
        }
    }

    private static void check(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new NullPointerException();
        }
    }

    private static void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
