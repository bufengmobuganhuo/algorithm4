package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;


import com.mengyu.algs4.utils.ArrayUtil;
import java.util.Arrays;
import java.util.Random;

/**
 * 练习1.4.20 在双调数组中查找某个整数
 */
public class EX_1_4_20 {
    public static void main(String[] args) {
        Random random=new Random();
        for (int i=0;i<100000;i++){
            Integer[] arr= ArrayUtil.createDoubleToneInt(20,100);
            //Integer[] arr={6, 7, 41, 43, 47, 50, 60, 63, 72, 77};
            int index=random.nextInt(arr.length);
            int res=find(arr,arr[index]);
            if (res!=index){
                System.out.println(Arrays.toString(arr));
                System.out.println("res:"+res+" target:"+arr[index]);
            }
        }
    }
    private static int find(Integer[] arr,Integer target){
        if (arr==null||arr.length==0){
            return -1;
        }
        int midIndx=findMid(arr,0,arr.length-1);
        if (arr[midIndx]>target){
            int res=binarySearch(arr,0,midIndx-1,true,target);
            return res!=-1?res :binarySearch(arr,midIndx+1, arr.length-1,false,target);
        }else if(arr[midIndx]<target){
            return -1;
        }else{
            return midIndx;
        }
    }
    private static int binarySearch(Integer[]arr,int start,int end,boolean isUp,int target){
        while (start<=end){
            int mid=(start+end)/2;
            if (arr[mid]==target){
                return mid;
            }else if(arr[mid]<target){
                return isUp?binarySearch(arr,mid+1,end,isUp,target):binarySearch(arr,start,mid-1,isUp,target);
            }else{
                return isUp?binarySearch(arr,start,mid-1,isUp,target):binarySearch(arr,mid+1,end,isUp,target);
            }
        }
        return -1;
    }
    private static int findMid(Integer[] arr,int start,int end){
        int mid=(start+end)/2;
        int midValue=arr[mid];
        while (start<=end){
            int midLeftValue=mid-1>=0?arr[mid-1]:Integer.MIN_VALUE;
            int midRightValue=mid+1<arr.length?arr[mid+1]:Integer.MIN_VALUE;
            if (midLeftValue<midValue&&midValue>midRightValue){
                return mid;
            }
            else if (midLeftValue<midValue&&midValue<midRightValue){
                return findMid(arr,mid+1,end);
            }
            else{
                return findMid(arr,start,mid-1);
            }
        }
        return -1;
    }
}
