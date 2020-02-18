package chapter2_Sorting.chapter2_1_ElementarySorts;

import chapter2_Sorting.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/7 11:29
 * 希尔排序
 */
public class ShellSort implements SortTemplate {

    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        int h=1;
        //初始化h的值
        while (h<arr.length/3){
            h=3*h+1;
        }
        while (h>=1){
            //使用插入排序,对间隔为h的元素进行排序
            for (int i=h;i<arr.length;i++){
                for (int j=h;j>0&&less(arr[j],arr[j-h]);j-=h){
                    exchange(arr,j,j-h);
                }
            }
            //更新h的值，当h=1时，则数组整体有序
            h=h/3;
        }
    }
}
