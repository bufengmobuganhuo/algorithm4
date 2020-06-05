package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import chapter2_Sorting.chapter2_4_PriorityQueues.PriorityQueue;
import edu.princeton.cs.algs4.StdRandom;
import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/22 15:12
 * 练习1.4.16：第一次尝试
 */
public class EX_1_4_16_1 {
    public static void main(String[] args) {
        //Integer[] arr= ArrayUtil.createInt(10,15);
        Integer[] arr={2, 6, 14, 12, 1, 10, 0, 9, 7, 4};
        System.out.println(Arrays.toString(arr));
        solution(arr);
    }
    public static void solution(Integer[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        sort(arr);
        int minValue=Integer.MAX_VALUE;
        int num1=0;
        int num2=0;
        for (int i=0;i<arr.length-1;i++){
            if (minValue>Math.abs(arr[i]-arr[i+1])){
                num1=arr[i];
                num2=arr[i];
            }
        }
        System.out.println(String.format("%d %d",num1,num2));
    }

    private static void sort(Integer[] arr){
        StdRandom.shuffle(arr);
        sort(arr,0,arr.length-1);
    }

    private static void sort(Integer[] arr,int start,int end){
        if (start>=end){
            return;
        }
        int partitionId= partition(arr,start, end);
        sort(arr,start,partitionId-1);
        sort(arr,partitionId+1,end);
    }

    private static int partition(Integer[] arr,int start,int end){
        int partitionEle=arr[start];
        int left=start,right=end+1;
        while (true){
            while (partitionEle>arr[++left]){
                if (left==end){
                    break;
                }
            }
            while (partitionEle<arr[--right]){
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

    private static void exch(Integer[] arr,int i,int j){
        Integer temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }


}
