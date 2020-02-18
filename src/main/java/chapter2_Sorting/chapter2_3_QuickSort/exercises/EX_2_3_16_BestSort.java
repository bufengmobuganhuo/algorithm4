package chapter2_Sorting.chapter2_3_QuickSort.exercises;

import com.sun.media.sound.RIFFInvalidDataException;
import utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/17 11:45
 * 练习2.3.16：最佳情况
 */
public class EX_2_3_16_BestSort {
    public static void main(String[] args) {
        Comparable[] arr=solution(8);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static Comparable[] solution(int len){
        if (len<=0){
            return null;
        }
        Comparable[] arr=new Comparable[len];
        //生成有序数组
        for (int i=0;i<len;i++){
            arr[i]=i;
        }
        generate(arr,0,arr.length-1);
        return arr;
    }
    public static void generate(Comparable[] arr,int start,int end){
        if (start>= end){
            return;
        }
        int mid=start+(end-start)/2;
        generate(arr,start,mid-1);
        generate(arr,mid+1,end);
        exchange(arr,mid,start);
    }
    public static void exchange(Comparable[] arr,int i,int j){
        Comparable temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
