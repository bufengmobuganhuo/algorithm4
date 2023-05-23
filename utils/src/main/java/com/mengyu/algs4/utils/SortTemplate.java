package com.mengyu.algs4.utils;

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

/**
 * 模板类
 */
public interface SortTemplate {
    /**
     * @return 判断value1是否小于value2
     */
    default boolean less(Comparable value1,Comparable value2){
        return value1.compareTo(value2)<0;
    }

    default void exchange(Comparable[] arr,int i,int j){
        Comparable temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    default boolean isSorted(Comparable[] arr){
        for (int i=1;i<arr.length;i++){
            if (less(arr[i],arr[i-1])){
                return false;
            }
        }
        return true;
    }

    default void show(String msg, Comparable[] arr){
        StdOut.println(msg);
        for (int i=0;i<arr.length;i++){
            StdOut.print(arr[i]+" ");
        }
        StdOut.println();
    }

    default void sort(Comparable[] arr){};
    default void shuffle(Comparable[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        Random random=new Random();
        int len=arr.length;
        for (int i=0;i<len;i++){
            int index=i+random.nextInt(len-i);
            exchange(arr,index,i);
        }
    }
}
