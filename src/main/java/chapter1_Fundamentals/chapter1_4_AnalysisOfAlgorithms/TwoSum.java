package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms;

import chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.BinarySerach;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 * 2-sum问题
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] arr= StdIn.readAllInts();
        System.out.println(getResult(arr));
    }

    public static int getResult(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        Arrays.sort(arr);
        int count=0;
        for (int i=0;i<arr.length;i++){
            //如果在arr[i]右边找到了-arr[i]，则加一
            if (BinarySerach.find(-arr[i],arr)>i){
                count++;
            }
        }
        return count;
    }
}
