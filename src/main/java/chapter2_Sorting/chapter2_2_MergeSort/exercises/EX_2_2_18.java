package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import chapter1_Fundamentals.utils.ArrayUtil;
import chapter2_Sorting.chapter2_1_ElementarySorts.Template;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/2/14 8:58
 * 练习2.2.18:打乱链表
 */
public class EX_2_2_18 {
    private static Comparable[] temp;
    private static Random random;

    public static void main(String[] args) {
        Comparable[] arr=ArrayUtil.createInt(15,20);
        System.out.println("打乱前："+ Arrays.toString(arr));
        solution(arr);
        System.out.println("打乱后："+ Arrays.toString(arr));
    }

    public static void solution(Comparable[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        temp=new Comparable[arr.length];
        random=new Random();
        random(arr,0,arr.length-1);
    }
    public static void random(Comparable[] arr,int left,int right){
        if (left>=right){
            return;
        }
        int mid=(left+right)/2;
        random(arr,left,mid);
        random(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    public static void merge(Comparable[] arr,int left,int mid,int right){
        int i=left,j=mid+1;
        System.arraycopy(arr,left,temp,left,right-left+1);
        for (int k=left;k<=right;k++){
            if (i>mid){
                arr[k]=temp[j++];
            }else if(j>right){
                arr[k]=temp[i++];
            }else{
                //从左右两边数组中随机取一个数进行归并
                arr[k]=random.nextDouble()<0.5?temp[i++]:temp[j++];
            }
        }
    }
}
