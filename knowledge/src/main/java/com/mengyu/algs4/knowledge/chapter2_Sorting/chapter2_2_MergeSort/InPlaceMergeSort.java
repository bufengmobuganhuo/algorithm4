package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort;

import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/7 11:55
 * 基于原地归并的归并排序
 */
public class InPlaceMergeSort implements SortTemplate {
    private static Comparable[] temp;

    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        temp=new Comparable[arr.length];
        sort(arr,0,arr.length-1);
    }

    public void sort(Comparable[] arr,int left,int right) {
        if (right<=left){
            return;
        }
        int mid=left+(left+right)/2;
        //对左半边排序
        sort(arr,left,mid);
        //对右半边排序
        sort(arr,mid+1,right);
        //归并左右半边
        inPlaceMerge(arr,left,mid,right);
    }

    /**
     * 原地归并
     * 使用辅助数组，将两个数组归并到新数组中
     */
    public void inPlaceMerge(Comparable[] arr,int left,int mid,int right){
        //分别代表左右半边数组的索引
        int i=left,j=mid+1;
        //将原数组的元素复制到辅助数组
        System.arraycopy(arr,left,temp,left,right-left+1);
        for (int k=left;k<=right;k++){
            //如果左边数组拿完，从右边数组取
            if (i>mid){
                arr[k]=temp[j++];
                //如果右边数组拿完，从左边拿
            }else if(j>right){
                arr[k]=temp[i++];
                //如果左边元素<右边元素，取左边
            }else if(less(temp[i],temp[j])){
                arr[k]=temp[i++];
            }else{//否则取右边
                arr[k]=temp[j++];
            }
        }

    }

}
