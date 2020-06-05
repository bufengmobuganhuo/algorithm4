package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/22 16:49
 * 练习1.4.18：第一次尝试
 */
public class EX_1_4_18_1 {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(10,15);
        System.out.println(Arrays.toString(arr));
        solution(arr);
    }
    public static void solution(Integer[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        int mid= findRecursive(arr,0,arr.length-1);
        System.out.println(String.format("%d %d %d",
                mid-1<0?null:arr[mid-1],
                mid<0?null:arr[mid],
                mid+1<arr.length?arr[mid+1]:null));
    }

    private static int findRecursive(Integer[] arr,int start,int end){
        if (start>=end){
            return -1;
        }
        if (arr[start]<arr[start+1]){
            return start;
        }
        if (arr[end-1]>arr[end]){
            return end;
        }
        int mid=start+(end-start)/2;
        if (arr[mid-1]>arr[mid]&&arr[mid]<arr[mid+1]){
            return mid;
        }
        if (arr[mid]>arr[mid-1]){
            return findRecursive(arr,start,mid);
        }
        if (arr[mid]>arr[mid+1]){
            return findRecursive(arr,mid,end);
        }
        return -1;
    }
}
