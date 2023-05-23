package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;

import com.mengyu.algs4.utils.ArrayUtil;
import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/11 11:46
 * 练习2.2.16：自然地归并排序
 */
public class EX_2_2_16 implements SortTemplate {
    private static Comparable[] temp;

    public static void main(String[] args) {
        Comparable[] arr= ArrayUtil.createInt(100000,200000);
        Comparable[] arr2=new Comparable[arr.length];
        for (int i=0;i<arr.length;i++){
            arr2[i]=arr[i];
        }
        //Comparable[] arr={6, 2, 19, 10, 19, 9, 16, 11, 9, 6};
        EX_2_2_16 ex_2_2_16=new EX_2_2_16();
        //ex_2_2_16.show("排序前：",arr);
        long start=System.currentTimeMillis();
        ex_2_2_16.sort2(arr);
        long end=System.currentTimeMillis();
        System.out.println(end-start);

        EX_2_2_16 ex_2_2_162=new EX_2_2_16();
        //ex_2_2_162.show("排序前：",arr2);
        start=System.currentTimeMillis();
        ex_2_2_162.sort(arr2);
        end=System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        temp=new Comparable[arr.length];

        int left=0;
        int mid=0;
        while (!(left==0&&mid==arr.length-1)){
            left=0;
            while (left<arr.length){
                //查找第一个有序块
                mid=left+findBlockSize(arr,left)-1;
                if (mid==arr.length-1){
                    break;
                }
                //查找第二个有序块
                int right=mid+findBlockSize(arr,mid+1);
                inPlaceMerge(arr,left,mid,right);
                left=right+1;
            }
        }
    }

    private void inPlaceMerge(Comparable[] arr,int left,int mid,int right){
        int i=left,j=mid+1;
        System.arraycopy(arr,left,temp,left,right-left+1);
        for (int k=left;k<=right;k++){
            //如果左边取完，从右边取
            if (i>mid){
                arr[k]=temp[j++];
                //右边取完从左边取
            }else if(j>right){
                arr[k]=temp[i++];
                //如果左边元素<右边元素，取左边元素
            }else if(less(temp[i],temp[j])){
                arr[k]=temp[i++];
            }else{
                arr[k]=temp[j++];
            }
        }
    }

    public void sort2(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        temp=new Comparable[arr.length];

        while (true)
        {
            // 找到第一个块
            int lo = 0;
            int mid = findBlockSize(arr,lo) - 1;
            if (mid == arr.length - 1)
                break;

            while (mid < arr.length - 1)
            {
                int hi = findBlockSize(arr,mid + 1) + mid;
                inPlaceMerge(arr,lo, mid, hi);
                lo = hi + 1;
                mid = findBlockSize( arr,lo) + lo - 1;
            }
        }
    }

    private static int findBlockSize(Comparable[] arr,int start){
        int size=1;
        while (start<arr.length-1&&arr[start].compareTo(arr[start+1])<=0){
            size++;
            start++;
        }
        return size;
    }
}
