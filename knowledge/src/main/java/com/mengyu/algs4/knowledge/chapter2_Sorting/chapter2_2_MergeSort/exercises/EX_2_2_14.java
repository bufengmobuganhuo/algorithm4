package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;

import com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.InPlaceMergeSort;
import com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_1_ElementarySorts.SelectionSort;
import com.mengyu.algs4.utils.ArrayUtil;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/2/11 11:04
 * 练习2.2.14：归并两个有序队列
 */
public class EX_2_2_14 {
    public static void main(String[] args) {
        SelectionSort selectionSort=new SelectionSort();
        Comparable[] arr1= ArrayUtil.createInt(10,20);
        selectionSort.sort(arr1);
        InPlaceMergeSort inPlaceMergeSort=new InPlaceMergeSort();
        Comparable[] arr2=ArrayUtil.createInt(10,20);
        inPlaceMergeSort.sort(arr2);
        Queue queue1=new ArrayDeque(10);
        for (Comparable item:arr1){
            System.out.print(item+" ");
            queue1.add(item);
        }
        Queue queue2=new ArrayDeque(10);
        System.out.println();
        for (Comparable item:arr2){
            System.out.print(item+" ");
            queue2.add(item);
        }
        System.out.println();
        Queue<Comparable> res=solution(queue1,queue2);
        for (Comparable item:res){
            System.out.print(item+" ");
        }
    }
    public static Queue<Comparable> solution(Queue<Comparable> queue1,Queue<Comparable> queue2){
        Queue<Comparable> res=new ArrayDeque<>(queue1.size()+queue2.size());
        while (!(queue1.isEmpty()&&queue2.isEmpty())){
            if (queue1.isEmpty()){
                res.add(queue2.poll());
            }else if(queue2.isEmpty()){
                res.add(queue1.poll());
            }else if(queue1.peek().compareTo(queue2.peek())<0){
                res.add(queue1.poll());
            }else{
                res.add(queue2.poll());
            }
        }
        return res;
    }

}
