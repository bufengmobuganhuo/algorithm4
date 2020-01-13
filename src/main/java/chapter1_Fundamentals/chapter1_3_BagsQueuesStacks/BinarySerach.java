package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 二分查找
 */
public class BinarySerach {
    public static int find(int target,int[] arr){
        int left=0;
        int right=arr.length-1;
        int mid=0;
        while (left<=right){
            mid=(right+left)/2;
            if (arr[mid]<target){
                left=mid+1;
            }else if(arr[mid]>target){
                right=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
