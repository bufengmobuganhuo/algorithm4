package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_3_QuickSort.exercises;

import com.mengyu.algs4.utils.SortTemplate;
import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Random;

/**
 * @author zhangyu
 * 2020/2/19 9:05
 * 练习2.3.22：快速三向切分
 */
public class Ex_2_3_22_Fast3WayPartitioning implements SortTemplate {
    public static void main(String[] args) {
        Random random=new Random();
        for (int i=0;i<10000;i++){
            int len=100;
            Comparable[] arr= ArrayUtil.createInt(len,2*len);
            Ex_2_3_22_Fast3WayPartitioning ex_2_3_22_fast3WayPartitioning=new Ex_2_3_22_Fast3WayPartitioning();
            Comparable[] temp=arr.clone();
            ex_2_3_22_fast3WayPartitioning.sort(arr);
            if (!ex_2_3_22_fast3WayPartitioning.isSorted(arr)){
                ex_2_3_22_fast3WayPartitioning.show("不正确:",temp);
            }
        }

    }
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        sort(arr,0,arr.length-1);
    }
    private void sort(Comparable[] arr,int lo, int hi){
        if (lo>=hi){
            return;
        }
        int i=lo,p=lo,j=hi+1,q=hi+1;
        Comparable partitionKey=arr[lo];
        while (true){
            while (less(arr[++i],partitionKey)){
                if (i==hi){
                    break;
                }
            };
            while (less(partitionKey,arr[--j])){
                if (j==lo){
                    break;
                }
            }
            if (i==j&&isEqual(arr[i],partitionKey)){
                exchange(arr,++p,i);
            }
            if (i>=j){
                break;
            }
            exchange(arr,i,j);
            if (isEqual(arr[i],partitionKey)){
                exchange(arr,++p,i);
            }
            if (isEqual(arr[j],partitionKey)){
                exchange(arr,--q,j);
            }
        }
        i = j + 1;
        for (int k = lo; k <= p; k++){
            exchange(arr, k, j--);
        }
        for (int k = hi; k >= q; k--){
            exchange(arr, k, i++);
        }
        sort(arr,lo,j);
        sort(arr,i,hi);
    }
    private boolean isEqual(Comparable value1, Comparable value2){
        return value1.compareTo(value2)==0;
    }
}
