package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import chapter1_Fundamentals.utils.ArrayUtil;
import chapter2_Sorting.chapter2_1_ElementarySorts.InsertionSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/2/14 10:14
 * 练习2.2.19:倒置
 */
public class EX_2_2_19 {
    private static Comparable[] temp;
    private static int count=0;
    public static void main(String[] args) {
        Random random=new Random();
        for (int i=0;i<10000;i++){
            int length=random.nextInt(100);
            Comparable[] arr= ArrayUtil.createInt(length,length*2);
            Comparable[] aux=arr.clone();
            Comparable[] arr2=arr.clone();
            InsertionSort insertionSort=new InsertionSort();
            if (insertionSort.sortCountExchange(arr)!=solution(arr2)){
                System.out.println("fail:"+ Arrays.toString(aux));
                solution(aux);
                break;
            }
            count=0;
        }
    }
    public static int solution(Comparable[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        temp=new Comparable[arr.length];
        return count(arr,0,arr.length-1);
    }
    public static int count(Comparable[] arr,int left,int right){
        if (left>=right){
            return 0;
        }
        int mid=(left+right)/2;
        count(arr,left,mid);
        count(arr,mid+1,right);
        merge(arr,left,mid,right);
        return count;
    }
    public static int merge(Comparable[] arr,int left,int mid,int right){
        int i=left,j=mid+1;
        System.arraycopy(arr,left,temp,left,right-left+1);
        for (int k=left;k<=right;k++){
            if (i>mid){
                arr[k]=temp[j];
                j++;
            }else if(j>right){
                arr[k]=temp[i];
                i++;
            }else if(less(temp[j],temp[i])){
                //当arr[j]<arr[i]时，就是逆序，此时需要移动的次数为:j-k
                count+=j-k;
                arr[k]=temp[j];
                j++;
            }else{
                arr[k]=temp[i];
                i++;
            }
        }
        return count;
    }
    public static boolean less(Comparable value1,Comparable value2){
        return value1.compareTo(value2)<0;
    }
}
