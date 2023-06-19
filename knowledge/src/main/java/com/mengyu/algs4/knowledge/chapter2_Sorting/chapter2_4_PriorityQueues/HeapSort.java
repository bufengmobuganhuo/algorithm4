package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues;

import com.mengyu.algs4.utils.ArrayUtil;
import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/20 15:52
 * 从小到大排序
 */
public class HeapSort implements SortTemplate {
    public static void main(String[] args) {
        Comparable[] arr = ArrayUtil.createInt(8, 10);
        HeapSort heapSort = new HeapSort();
        heapSort.show("排序前：", arr);
        heapSort.sort(arr);
        heapSort.show("排序后：", arr);
    }

    @Override
    public void sort(Comparable[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int length = arr.length;
        //构造堆
        for (int k = (length - 1) / 2; k >= 0; k--) {
            sink(arr, k, length);
        }
        while (length > 0) {
            //交换根节点和最后一个元素的位置，即是删除堆顶元素
            exchange(arr, 0, --length);
            sink(arr, 0, length);
        }
    }

    /**
     * 下沉操作
     *
     * @param arr
     * @param k   待下沉元素
     * @param N   数组中最后一个元素的索引
     */
    private void sink(Comparable[] arr, int k, int N) {
        while (2 * k + 1 < N) {
            int j = 2 * k + 1;
            //左右子节点中取较大值
            if (j + 1 < N && less(arr[j], arr[j + 1])) {
                j++;
            }
            //如果待下沉元素>左右子节点的最大值，则停止下沉
            if (less(arr[j], arr[k])) {
                break;
            }
            //左右子节点的大小关系不确定，所以这里只和较大子节点进行交换
            exchange(arr, j, k);
            k = j;
        }
    }
}
