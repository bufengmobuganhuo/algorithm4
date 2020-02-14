package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import chapter1_Fundamentals.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/10 16:59
 * TODO
 */
public class TestMain {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(40,45);
        EX_2_2_11_ImproveMerge quickMerge=new EX_2_2_11_ImproveMerge();
        quickMerge.show("排序前：",arr);
        quickMerge.sort(arr);
        quickMerge.show("排序后：",arr);


    }
}
