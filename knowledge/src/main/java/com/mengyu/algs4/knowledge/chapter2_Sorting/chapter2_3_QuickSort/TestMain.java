package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_3_QuickSort;

import com.mengyu.algs4.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/16 16:37
 * TODO
 */
public class TestMain {
    public static void main(String[] args) {
        //Comparable[] arr= ArrayUtil.createInt(15,20);
        Comparable[] arr={7897987,999,986768,3451};
        QuickSort quickSort=new QuickSort();
        quickSort.show("排序前:",arr);
        quickSort.sort(arr);
        quickSort.show("排序后:",arr);
        Comparable[] arr2= ArrayUtil.createInt(15,20);
        Quick3WaySort quick3WaySort=new Quick3WaySort();
        quick3WaySort.show("排序前:",arr2);
        quick3WaySort.sort(arr2);
        quick3WaySort.show("排序后:",arr2);
    }
}
