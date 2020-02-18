package chapter2_Sorting.chapter2_2_MergeSort;

import utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/7 12:13
 * TODO
 */
public class TestMain {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(10,20,false);
        InPlaceMergeSort inPlaceMergeSort=new InPlaceMergeSort();
        inPlaceMergeSort.show("排序前:",arr);
        inPlaceMergeSort.sort(arr);
        inPlaceMergeSort.show("归并排序后:",arr);

        MergeFromBottomToTop mergeFromBottomToTop=new MergeFromBottomToTop();
        mergeFromBottomToTop.sort(arr);
        mergeFromBottomToTop.show("归并排序后：",arr);
    }
}
