package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_3_QuickSort.exercises;

import com.mengyu.algs4.utils.SortTemplate;
import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Random;

/**
 * @author zhangyu
 * 2020/2/18 14:27
 * 练习2.3.17：哨兵
 */
public class EX_2_3_17_Sentinels implements SortTemplate {
    public static void main(String[] args) {
        Comparable[] arr= ArrayUtil.createInt(8,15);
        EX_2_3_17_Sentinels ex_2_3_17_sentinels=new EX_2_3_17_Sentinels();
        ex_2_3_17_sentinels.show("排序前:",arr);
        ex_2_3_17_sentinels.sort(arr);
        ex_2_3_17_sentinels.show("排序后:",arr);
    }
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        shuffle(arr);
        int maxIdx=0;
        for (int i=0;i<arr.length;i++){
            maxIdx=arr[maxIdx].compareTo(arr[i])<0?i:maxIdx;
        }
        exchange(arr,maxIdx,arr.length-1);
        sort(arr,0,arr.length-1);
    }
    private void sort(Comparable[] arr,int start,int end){
        if (start>= end){
            return;
        }
        int partitionIdx=partition(arr,start,end);
        sort(arr,start,partitionIdx-1);
        sort(arr,partitionIdx+1,end);
    }
    private int partition(Comparable[] arr,int start,int end){
        int left=start,right=end+1;
        Comparable partitionKey=arr[start];
        while (true){
            while (less(arr[++left],partitionKey)){};
            while (less(partitionKey,arr[--right])){};
            if (left>=right){
                break;
            }
            exchange(arr,left,right);
        }
        exchange(arr,start,right);
        return right;
    }
    @Override
    public void shuffle(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        Random random=new Random();
        for (int i=0;i<arr.length;i++){
            int index=i+random.nextInt(arr.length-i);
            exchange(arr,i,index);
        }

    }
}
