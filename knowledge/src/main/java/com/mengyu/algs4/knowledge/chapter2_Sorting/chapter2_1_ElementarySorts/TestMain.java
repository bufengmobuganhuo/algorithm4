package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_1_ElementarySorts;

import com.mengyu.algs4.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/7 9:23
 * TODO
 */
public class TestMain {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(10,20);
        SelectionSort selectionSort=new SelectionSort();
        selectionSort.show("排序前:",arr);
        selectionSort.sort(arr);
        selectionSort.show("排序后:",arr);

        InsertionSort insertionSort=new InsertionSort();
        insertionSort.sort(arr);
        insertionSort.show("插入排序后：",arr);

        ShellSort shellSort=new ShellSort();
        shellSort.sort(arr);
        shellSort.show("希尔排序后：",arr);

        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(arr);
        bubbleSort.show("冒泡排序后：",arr);
    }
}
