package chapter2_Sorting.chapter2_5_Applications.exercises;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/3/12 14:20
 * 练习2.5.27：平行数组排序
 */
public class EX_2_5_27_ParallelArrSort {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(8,15);
        System.out.println(Arrays.toString(arr));
        int[] index=solution(arr);
        for (int i=0;i<index.length;i++){
            System.out.print(arr[index[i]]+" ");
        }
    }
    public static int[] solution(Comparable[] arr){
        if (arr==null||arr.length==0){
            return null;
        }
        int[] index=new int[arr.length];
        for (int i=0;i<index.length;i++){
            index[i]=i;
        }
        for (int i=1;i<index.length;i++){
            for (int j=i;j>0&&less(arr,index[j],index[j-1]);j--){
                exch(index,j-1,j);
            }
        }
        return index;
    }
    private static boolean less(Comparable[] arr,int i,int j){
        return arr[i].compareTo(arr[j])<0;
    }
    private static void exch(int[] index,int i,int j){
        int temp=index[i];
        index[i]=index[j];
        index[j]=temp;
    }
}
