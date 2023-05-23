package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/3/6 21:16
 * 练习2.5.6：找出第k小的元素
 */
public class EX_2_5_6 {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(10,20);
        System.out.println(Arrays.toString(arr));
        System.out.println(solution2(arr,3));
    }
    //方法二：递归版本
    public static Comparable solution2(Comparable[] arr,int k){
        if (arr==null||arr.length==0||k<0){
            return null;
        }
        return findMinK(arr,0,arr.length-1,k);
    }

    public static Comparable findMinK(Comparable[] arr,int start,int end,int k){
        if (start>end){
            return null;
        }
        int partitionIdx=partition(arr,start,end);
        if (partitionIdx==k){
            return arr[k];
        }else if(partitionIdx>k){
            return findMinK(arr,start,partitionIdx-1,k);
        }else{
            return findMinK(arr,partitionIdx+1,end,k);
        }
    }
    //方法一
    public static Comparable solution1(Comparable[] arr,int k){
        if (arr==null||arr.length==0||k<0){
            return null;
        }
        int start=0,end=arr.length-1;
        while (start<=end){
            //切分返回的索引就是切分元素在排序后应该在的位置，故可以用来获取第k小的元素
            int partitionIdx=partition(arr,start,end);
            if (partitionIdx==k){
                return arr[k];
            }else if(partitionIdx>k){
                end=partitionIdx-1;
            }else{
                start=partitionIdx+1;
            }
        }
        return null;
    }
    private static int partition(Comparable[] arr,int start,int end){
        int left=start,right=end+1;
        Comparable partitionKey=arr[start];
        while (true){
            while (partitionKey.compareTo(arr[++left])>0){
                if(left==end){
                    break;
                }
            }
            while (partitionKey.compareTo(arr[--right])<0){
                if (right==start){
                    break;
                }
            }
            if (left>=right){
                break;
            }
            exch(arr,left,right);
        }
        exch(arr,start,right);
        return right;
    }
    private static void exch(Comparable[] arr,int i,int j){
        Comparable temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

}
