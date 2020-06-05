package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/22 16:17
 * 练习1.4.17：
 */
public class EX_1_4_17_1 {
    public static void main(String[] args) {
        double[] arr=ArrayUtil.createDouble(10,15);
        System.out.println(Arrays.toString(arr));
        solution(arr);
    }
    public static void solution(double[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        sort(arr);
        double min=Double.POSITIVE_INFINITY;
        double max=Double.NEGATIVE_INFINITY;
        for (int i=0;i<arr.length;i++){
            min=Math.min(arr[i],min);
            max=Math.max(arr[i],max);
        }
        System.out.println(String.format("%.2f %.2f:%.2f",min,max,Math.abs(min-max)));
    }

    private static void sort(double[] arr){
        sort(arr,0,arr.length-1);
    }

    private static void sort(double[] arr,int start,int end){
        if (start>=end){
            return;
        }
        int partitionIdx=partition(arr,start,end);
        sort(arr,start,partitionIdx-1);
        sort(arr,partitionIdx+1, end);
    }

    private static int partition(double[] arr,int start,int end){
        double partitionEle=arr[start];
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

    private static void exch(double[] arr,int i,int j){
        double temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
