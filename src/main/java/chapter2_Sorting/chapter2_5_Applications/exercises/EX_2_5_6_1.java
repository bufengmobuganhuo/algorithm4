package chapter2_Sorting.chapter2_5_Applications.exercises;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/6/27 9:23 上午
 * 练习2.5.6：第一次尝试
 */
public class EX_2_5_6_1 {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(8,15);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        EX_2_5_6_1 ex_2_5_6_1=new EX_2_5_6_1();
        System.out.println(ex_2_5_6_1.select(arr,4));
    }
    public Integer select(Integer[] arr, int k) {
        if (k>arr.length-1){
            return -1;
        }
        return select(arr,0,arr.length-1,k);
    }

    private Integer select(Integer[] arr, int lo, int hi, int k) {
        if (lo >= hi) {
            return -1;
        }
        int partitionIdx = partition(arr, lo, hi);
        if (partitionIdx == k) {
            return arr[k];
        } else if (partitionIdx > k) {
            return select(arr, lo, partitionIdx - 1, k);
        } else {
            return select(arr, partitionIdx + 1, hi, k);
        }
    }

    private int partition(Integer[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        Integer partitionKey = arr[lo];
        while (true) {
            while (arr[--j] > partitionKey) {
                if (j == lo) {
                    break;
                }
            }
            while (arr[++i] < partitionKey) {
                if (i == hi) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(arr, i, j);
        }
        exch(arr, j, lo);
        return j;
    }

    private void exch(Integer[] arr, int i, int j) {
        Integer tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
