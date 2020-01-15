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
            int[] arr= ArrayUtil.createInt(length,100);
            List<Integer> res= run(arr);
            if (res.size()<=0){
                continue;
            }
            for (int idx:res){
                if (arr[idx]>arr[idx-1]||arr[idx]>arr[idx+1]){
                    StdOut.println("idx:"+idx+" "+Arrays.toString(arr));
                }
            }
        }
    }

    private static List<Integer> run(int[] arr){
        if (arr==null||arr.length==0){
            return new ArrayList<Integer>();
        }
        List<Integer> res=new ArrayList<Integer>(arr.length);
        findLocalMinimumRecursive(arr,0,arr.length-1,res);
        return res;
    }
    /**
     * 递归版本
     */
    private static void findLocalMinimumRecursive(int[] arr, int start, int end, List<Integer>res){
        if(end-start<2){
            return;
        }
        int mid=(start+end)/2;
        if (arr[mid]<arr[mid-1]&&arr[mid]<arr[mid+1]){
            res.add(mid);
        }
        //从左边找
        findLocalMinimumRecursive(arr,start,mid,res);
        //从右边找
        findLocalMinimumRecursive(arr,mid,end,res);
    }
}
