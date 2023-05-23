package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_3_QuickSort;

import com.mengyu.algs4.utils.SortTemplate;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author zhangyu
 * 2020/2/16 17:27
 * 三向切分的快速排序
 */
public class Quick3WaySort implements SortTemplate {
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        StdRandom.shuffle(arr);
        sort(arr,0,arr.length-1);
    }
    private void sort(Comparable[] arr,int start,int end){
        if (start>=end){
            return;
        }
        int lessPointer=start,i=start+1,greaterPointer=end;
        Comparable partitionKey=arr[start];
        while (i<=greaterPointer){
            int cmpRes=arr[i].compareTo(partitionKey);
            //若arr[i]<切分元素，则交换arr[i],arr[lt]
            if (cmpRes<0){
                exchange(arr,i++,lessPointer++);
                //若arr[i]=切分元素，则i++
            }else if(cmpRes==0){
                i++;
                //arr[i]>切分元素，则交换arr[i],arr[gt]
                //此时还不知道arr[gt]是否<或=切分元素，则交换之后i不变，再比较一次
            }else{
                exchange(arr,i,greaterPointer--);
            }
        }
        sort(arr,start,lessPointer-1);
        sort(arr,greaterPointer+1,end);
    }
}
