package chapter2_Sorting.chapter2_1_ElementarySorts;

import chapter2_Sorting.SortTemplate;

/**
 * 选择排序
 */
public class SelectionSort implements SortTemplate {

    @Override
    public void sort(Comparable[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            //从i的后面开始查找一个最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[minIndex])) {
                    minIndex = j;
                }
            }
            //将找到的最小值和i交换
            exchange(arr, minIndex, i);
        }
    }
}
