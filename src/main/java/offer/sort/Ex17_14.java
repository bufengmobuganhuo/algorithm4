package offer.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex17_14 {
    public static void main(String[] args) {
        Ex17_14 ex17_14 = new Ex17_14();
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        System.out.println(Arrays.toString(ex17_14.smallestK(arr, 4)));
    }

    private int idx;

    private int[] res;

    // 使用快速排序的切分，切出来的位置就是实际排序后的位置，从而可以知道是否是需要的结果
    public int[] smallestK(int[] arr, int k) {
        res = new int[k];
        idx = 0;
        sort(arr, k, 0, arr.length - 1);
        return res;
    }

    private void sort(int[] arr, int k, int start, int end) {
        if (idx >= k) {
            return;
        } else if (start > end) {
            return;
        } else if (start == end) {
            if (start < k) {
                res[idx++] = arr[start];
            }
            return;
        }
        int partitionIdx = partition(arr, start, end);
        if (partitionIdx < k) {
            res[idx++] = arr[partitionIdx];
        }
        sort(arr, k,partitionIdx + 1, end);
        sort(arr, k, start, partitionIdx - 1);
    }

    private int partition(int[] arr, int start, int end) {
        int partitionEle = arr[start];
        int leftPtr = start, rightPtr = end + 1;
        while (true) {
            while (arr[++leftPtr] < partitionEle) {
                if (leftPtr >= end) {
                    break;
                }
            }
            while (arr[--rightPtr] > partitionEle) {
                if (rightPtr <= start) {
                    break;
                }
            }
            if (leftPtr >= rightPtr) {
                break;
            }
            exch(arr, leftPtr, rightPtr);
        }
        if (start == rightPtr) {
            return rightPtr;
        }
        exch(arr, start, rightPtr);
        return rightPtr;
    }

    private void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
