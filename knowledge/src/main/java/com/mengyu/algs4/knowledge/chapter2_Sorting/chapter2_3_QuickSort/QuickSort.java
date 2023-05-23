package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_3_QuickSort;

import com.mengyu.algs4.utils.SortTemplate;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author zhangyu
 * 2020/2/16 16:24
 * 快速排序
 */
public class QuickSort implements SortTemplate {
    @Override
    public void sort(Comparable[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    public void sort(Comparable[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        //找到切分元素位置
        int partitionIndex = partition(arr, start, end);
        //对切分元素的左右两边分别进行递归切分
        sort(arr, start, partitionIndex - 1);
        sort(arr, partitionIndex + 1, end);
    }

    public int partition(Comparable[] arr, int start, int end) {
        //左右指针
        int left = start, right = end + 1;
        //切分元素
        Comparable partitionKey = arr[start];
        while (true) {
            //左指针向右扫描，直到遇到一个元素>=切分元素
            while (less(arr[++left], partitionKey)) {
                //到达边界，则跳出循环
                if (left == end) {
                    break;
                }
            }
            //右指针向左扫描，直到遇到一个元素<=切分元素
            while (less(partitionKey, arr[--right])) {
                if (right == start) {
                    break;
                }
            }
            //如果两个指针相遇，则说明已切分完成，切分元素已经到了应该在的位置
            if (left >= right) {
                break;
            }
            exchange(arr, left, right);
        }
        //上述循环找到了切分元素应该在的位置，则将其放到应该在的位置上
        exchange(arr, right, start);
        return right;
    }
}
