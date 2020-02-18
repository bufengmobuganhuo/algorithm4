package chapter2_Sorting.chapter2_3_QuickSort.exercises;

import chapter2_Sorting.SortTemplate;
import chapter2_Sorting.chapter2_1_ElementarySorts.InsertionSort;
import chapter2_Sorting.chapter2_2_MergeSort.exercises.EX_2_2_20;
import edu.princeton.cs.algs4.In;
import utils.ArrayUtil;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author zhangyu
 * 2020/2/18 15:20
 * 练习2.3.19：五取样切分
 */
public class EX_2_3_19 implements SortTemplate {
    public static void main(String[] args) {
        Comparable[] arr= ArrayUtil.createInt(8,15);
        EX_2_3_19 ex_2_3_19=new EX_2_3_19();
        ex_2_3_19.show("排序前:",arr);
        ex_2_3_19.sort(arr);
        ex_2_3_19.show("排序后:",arr);
    }
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        shuffle(arr);
        sort(arr,0,arr.length-1);
    }
    private void sort(Comparable[] arr,int start,int end){
        if (start>=end){
            return;
        }
        int partitionKey=partition(arr,start,end);
        sort(arr,start,partitionKey-1);
        sort(arr,partitionKey+1,end);
    }
    private int partition(Comparable[] arr, int start,int end){
        int left=start,right=end+1;
        int partitionIdx=findMid(arr,start,start+Math.min(4,end-start));
        exchange(arr,partitionIdx,start);
        Comparable partitionKey=arr[start];
        while (true){
            while (arr[++left].compareTo(partitionKey)<0){
                if (left==end){
                    break;
                }
            };
            while (partitionKey.compareTo(arr[--right])<0){
                if (right==start){
                    break;
                }
            };
            if (left>=right){
                break;
            }
            exchange(arr,left,right);
        }
        exchange(arr,start,right);
        return right;
    }
    private int findMid(Comparable[] arr,int start,int end){
        Comparable[] temp=new Comparable[end-start+1];
        for (int i=0;i<temp.length;i++){
            temp[i]=arr[start+i];
        }
        Integer[] index= insertionSort(temp);
        return start+index[index.length/2];
    }
    private Integer[] insertionSort(Comparable[] arr){
        if (arr==null||arr.length==0){
            return null;
        }
        Integer[] index=new Integer[arr.length];
        for (int i=0;i<index.length;i++){
            index[i]=i;
        }
        for (int i = 1;i<arr.length;i++){
            for (int j=i;j>0&&less(arr[index[j]],arr[index[j-1]]);j--){
                exchange(index,j,j-1);
            }
        }
        return index;
    }
}
