package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import chapter1_Fundamentals.utils.ArrayUtil;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 练习1.4.18：寻找局部最小值
 */
public class EX_1_4_18 {
    public static void main(String[] args) {
        Random random=new Random();
        for (int i=0;i<10000;i++){
            int length=random.nextInt(10);
            if (length<=0){
                continue;
            }
            Integer[] arr= ArrayUtil.createInt(length,100,false);
            int idx=  run(arr);
            if (idx==-1&&arr.length>2){
                StdOut.println("idx:"+idx+" "+Arrays.toString(arr));
            }
            if (idx-1>=0&&idx+1<arr.length){
                if(arr[idx]>arr[idx-1]||arr[idx]>arr[idx+1]){
                    StdOut.println("idx:"+idx+" "+Arrays.toString(arr));
                }
            }
        }
    }

    private static int run(Integer[] arr){
        if (arr==null||arr.length<=1){
            return -1;
        }
        return findLocalMinimumRecursive(arr,0,arr.length-1);
    }
    /**
     * 递归版本：在一个具有不同整数的数组里，局部最小值是一定存在的,因为几个数中，总有一个是最小值
     * 1. 先看左右边界和中间位置是否符合条件，符合返回
     * 2. 看中间位置：
     *   （1）符合条件，则返回
     *  （将范围缩小后，又是一个新的数组，在保证边界条件的前提下，局部最小值一定存在）
     *  （2）/（中间数的左边<中间数）：向左递归查找（这个数的右边已经满足了）
     *  （3）\（中间数>中间数的右边）：享有递归查找（这个数的左边已经满足了）
     *  （4）/\：不可能，因为有边界条件（递归是从小到大范围，如果边界条件满足，已经返回了）
     */
    private static int findLocalMinimumRecursive(Integer[] arr, int start, int end){
        //判断边界
        if(arr[start]<arr[start+1]){
            return start;
        }
        if (arr[end-1]>arr[end]){
            return end;
        }
        int mid=(start+end)/2;
        if (arr[mid]<arr[mid-1]&&arr[mid]<arr[mid+1]){
            return mid;
        }
        if (arr[mid-1]<arr[mid]){
            return findLocalMinimumRecursive(arr,start,mid);
        }
        if (arr[mid]>arr[mid+1]){
            return findLocalMinimumRecursive(arr,mid,end);
        }
        return -1;
    }
}
