package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_1_ElementarySorts.exercises;


import com.mengyu.algs4.utils.ArrayUtil;
import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/21 14:36
 * TODO
 */
public class EX_2_1_25_InsertionX_1 {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(15,20);
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(Integer[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        for (int i=1;i<arr.length;i++){
            for (int j=i;j>0&&less(arr[j],arr[j-1]);j--){
                exch(arr,j,j-1);
            }
        }
    }

    private static void exch(Integer[] arr,int i,int j){
        Integer temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    private static boolean less(Integer value1,Integer value2){
        return value1<value2;
    }
}
